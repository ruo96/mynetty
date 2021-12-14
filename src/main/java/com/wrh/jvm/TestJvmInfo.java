package com.wrh.jvm;

import com.alibaba.fastjson.JSONObject;
import com.wrh.utils.FormatUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.FileSystem;
import oshi.software.os.NetworkParams;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author wuruohong
 * @Classname TestJvmInfo
 * @Description TODO
 * @Date 2021/12/6 11:13
 */
@Slf4j
public class TestJvmInfo {


    /**
     * 第一个种方法通过ManagementFactory获取MemoryMXBean
     */
    @Test
    public void Test11() {
        MemoryMXBean mxb = ManagementFactory.getMemoryMXBean();
        //堆
        System.out.println("Max:" + FormatUtils.formatByte(mxb.getHeapMemoryUsage().getMax()));    //Max:1776MB
        System.out.println("Init:" + FormatUtils.formatByte(mxb.getHeapMemoryUsage().getInit()));  //Init:126MB
        System.out.println("Committed:" + FormatUtils.formatByte(mxb.getHeapMemoryUsage().getCommitted()));   //Committed:121MB
        System.out.println("Used:" + FormatUtils.formatByte(mxb.getHeapMemoryUsage().getUsed()));  //Used:7MB
        System.out.println(mxb.getHeapMemoryUsage().toString());
        //直接内存
        System.out.println("Max:" +FormatUtils.formatByte(mxb.getNonHeapMemoryUsage().getMax()));    //Max:0MB
        System.out.println("Init:" + FormatUtils.formatByte(mxb.getNonHeapMemoryUsage().getInit()));  //Init:2MB
        System.out.println("Committed:" + FormatUtils.formatByte(mxb.getNonHeapMemoryUsage().getCommitted()));   //Committed:8MB
        System.out.println("Used:" + FormatUtils.formatByte(mxb.getNonHeapMemoryUsage().getUsed()));  //Used:7MB
        System.out.println(mxb.getNonHeapMemoryUsage().toString());
    }

    /**
     * 使用Runtime对象去获取
     */
    @Test
    public void Test39() {
        JSONObject cpuInfo = new JSONObject();
        Properties props = System.getProperties();
        Runtime runtime = Runtime.getRuntime();
        long jvmTotalMemoryByte = runtime.totalMemory();
        long freeMemoryByte = runtime.freeMemory();
        //jvm总内存
        cpuInfo.put("total", FormatUtils.formatByte(jvmTotalMemoryByte));
        //空闲空间
        cpuInfo.put("free", FormatUtils.formatByte(freeMemoryByte));
        //jvm最大可申请
        cpuInfo.put("max", FormatUtils.formatByte(runtime.maxMemory()));
        //jvm已使用内存
        cpuInfo.put("user", FormatUtils.formatByte(jvmTotalMemoryByte - freeMemoryByte));
        //jvm内存使用率
        cpuInfo.put("usageRate", new DecimalFormat("#.##%").format((jvmTotalMemoryByte - freeMemoryByte) * 1.0 / jvmTotalMemoryByte));
        //jdk版本
        cpuInfo.put("jdkVersion", props.getProperty("java.version"));
        //jdk路径
        cpuInfo.put("jdkHome", props.getProperty("java.home"));

        System.out.println(cpuInfo);

    }

    @Test
    public void Test71() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        Map<String, Number> map = new LinkedHashMap<String, Number>();
// 线程总数
        map.put("jvm.thread.count", threadBean.getThreadCount());
// 守护线程总数
        map.put("jvm.thread.daemon.count", threadBean.getDaemonThreadCount());
// 开启线程总数
        map.put("jvm.thread.totalstarted.count", threadBean.getTotalStartedThreadCount());
// 获取线程详细信息
        ThreadInfo[] threadInfos = threadBean.getThreadInfo(threadBean.getAllThreadIds());

        int newThreadCount = 0;
        int runnableThreadCount = 0;
        int blockedThreadCount = 0;
        int waitThreadCount = 0;
        int timeWaitThreadCount = 0;
        int terminatedThreadCount = 0;
        if (threadInfos != null) {
            for (ThreadInfo threadInfo : threadInfos) {
                if (threadInfo != null) {
                    switch (threadInfo.getThreadState()) {
                        case NEW:
                            newThreadCount++;
                            break;
                        case RUNNABLE:
                            runnableThreadCount++;
                            break;
                        case BLOCKED:
                            blockedThreadCount++;
                            break;
                        case WAITING:
                            waitThreadCount++;
                            break;
                        case TIMED_WAITING:
                            timeWaitThreadCount++;
                            break;
                        case TERMINATED:
                            terminatedThreadCount++;
                            break;
                        default:
                            break;
                    }
                } else {

                    terminatedThreadCount++;
                }
            }
// 新建线程数
            map.put("jvm.thread.new.count", newThreadCount);
// 运行线程数
            map.put("jvm.thread.runnable.count", runnableThreadCount);
// 阻塞线程数
            map.put("jvm.thread.blocked.count", blockedThreadCount);
// 等待线程数
            map.put("jvm.thread.waiting.count", waitThreadCount);
// 等待超时线程数
            map.put("jvm.thread.time_waiting.count", timeWaitThreadCount);
// 死亡线程数
            map.put("jvm.thread.terminated.count", terminatedThreadCount);

            long[] ids = threadBean.findDeadlockedThreads();
            map.put("jvm.thread.deadlock.count", ids == null ? 0 : ids.length);

        }
    }

    /**
     * 获取系统cpu信息
     * 直接使用oshi的系统api
     */
    @Test
    public void Test143() {
        JSONObject cpuInfo = new JSONObject();
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hardware = si.getHardware();
        CentralProcessor processor = hardware.getProcessor();
        // CPU信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        long OSHI_WAIT_SECOND = 10;
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        //cpu核数
        cpuInfo.put("cpuNum", processor.getLogicalProcessorCount());
        //cpu系统使用率
        cpuInfo.put("cSys", new DecimalFormat("#.##%").format(cSys * 1.0 / totalCpu));
        //cpu用户使用率
        cpuInfo.put("user", new DecimalFormat("#.##%").format(user * 1.0 / totalCpu));
        //cpu当前等待率
        cpuInfo.put("iowait", new DecimalFormat("#.##%").format(iowait * 1.0 / totalCpu));
        //cpu当前使用率
        cpuInfo.put("idle", new DecimalFormat("#.##%").format(1.0 - (idle * 1.0 / totalCpu)));
        System.out.println(cpuInfo);

    }

    @Test
    public void Test178() {
        // Options: ERROR > WARN > INFO > DEBUG > TRACE

        log.info("Initializing System...");
        SystemInfo si = new SystemInfo();

        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        System.out.println(os);

        log.info("Checking computer system...");
        printComputerSystem(hal.getComputerSystem());

        log.info("Checking Processor...");
        printProcessor(hal.getProcessor());

        log.info("Checking Memory...");
        printMemory(hal.getMemory());

        log.info("Checking CPU...");
        printCpu(hal.getProcessor());


        log.info("Checking Sensors...");
        printSensors(hal.getSensors());

        log.info("Checking Power sources...");
        printPowerSources(hal.getPowerSources());

        log.info("Checking Disks...");
        printDisks(hal.getDiskStores());

        log.info("Checking File System...");
        printFileSystem(os.getFileSystem());

        log.info("Checking Network interfaces...");
        printNetworkInterfaces(hal.getNetworkIFs());

        log.info("Checking Network parameterss...");
        printNetworkParameters(os.getNetworkParams());

        // hardware: displays
        log.info("Checking Displays...");
        printDisplays(hal.getDisplays());

        // hardware: USB devices
        log.info("Checking USB Devices...");
        printUsbDevices(hal.getUsbDevices(true));

    }

    private static void printComputerSystem(final ComputerSystem computerSystem) {

        System.out.println("manufacturer: " + computerSystem.getManufacturer());
        System.out.println("model: " + computerSystem.getModel());
        System.out.println("serialnumber: " + computerSystem.getSerialNumber());
        final Firmware firmware = computerSystem.getFirmware();
        System.out.println("firmware:");
        System.out.println("  manufacturer: " + firmware.getManufacturer());
        System.out.println("  name: " + firmware.getName());
        System.out.println("  description: " + firmware.getDescription());
        System.out.println("  version: " + firmware.getVersion());
        final Baseboard baseboard = computerSystem.getBaseboard();
        System.out.println("baseboard:");
        System.out.println("  manufacturer: " + baseboard.getManufacturer());
        System.out.println("  model: " + baseboard.getModel());
        System.out.println("  version: " + baseboard.getVersion());
        System.out.println("  serialnumber: " + baseboard.getSerialNumber());
    }

    private static void printProcessor(CentralProcessor processor) {
        System.out.println(processor);
        System.out.println(" " + processor.getPhysicalPackageCount() + " physical CPU package(s)");
        System.out.println(" " + processor.getPhysicalProcessorCount() + " physical CPU core(s)");

        System.out.println("Identifier: " + processor.getProcessorIdentifier());
        System.out.println("ProcessorID: " + processor.getProcessorIdentifier());
    }

    private static void printMemory(GlobalMemory memory) {
        System.out.println("Memory: " + FormatUtil.formatBytes(memory.getAvailable()) + "/"
                + FormatUtil.formatBytes(memory.getTotal()));
        System.out.println("Swap used: " + FormatUtil.formatBytes(memory.getVirtualMemory().getSwapUsed()) + "/"
                + FormatUtil.formatBytes(memory.getVirtualMemory().getSwapTotal()));
    }

    private static void printCpu(CentralProcessor processor) {
        System.out.println(
                "Context Switches/Interrupts: " + processor.getContextSwitches() + " / " + processor.getInterrupts());

        long[] prevTicks = processor.getSystemCpuLoadTicks();
        System.out.println("CPU, IOWait, and IRQ ticks @ 0 sec:" + Arrays.toString(prevTicks));
        // Wait a second...
        long[] ticks = processor.getSystemCpuLoadTicks();
        System.out.println("CPU, IOWait, and IRQ ticks @ 1 sec:" + Arrays.toString(ticks));
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;

        System.out.format(
                "User: %.1f%% Nice: %.1f%% System: %.1f%% Idle: %.1f%% IOwait: %.1f%% IRQ: %.1f%% SoftIRQ: %.1f%% Steal: %.1f%%%n",
                100d * user / totalCpu, 100d * nice / totalCpu, 100d * sys / totalCpu, 100d * idle / totalCpu,
                100d * iowait / totalCpu, 100d * irq / totalCpu, 100d * softirq / totalCpu, 100d * steal / totalCpu);
        double[] loadAverage = processor.getSystemLoadAverage(3);
        System.out.println("CPU load averages:" + (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
                + (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
                + (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));
    }

    private static void printSensors(Sensors sensors) {
        System.out.println("Sensors:");
        System.out.format(" CPU Temperature: %.1f°C%n", sensors.getCpuTemperature());
        System.out.println(" Fan Speeds: " + Arrays.toString(sensors.getFanSpeeds()));
        System.out.format(" CPU Voltage: %.1fV%n", sensors.getCpuVoltage());
    }

    private static void printPowerSources(List<PowerSource> powerSources) {
        StringBuilder sb = new StringBuilder("Power: ");
        if (powerSources.size() == 0) {
            sb.append("Unknown");
        } else {
            double timeRemaining = powerSources.get(0).getTimeRemainingInstant();
            if (timeRemaining < -1d) {
                sb.append("Charging");
            } else if (timeRemaining < 0d) {
                sb.append("Calculating time remaining");
            } else {
                sb.append(String.format("%d:%02d remaining", (int) (timeRemaining / 3600),
                        (int) (timeRemaining / 60) % 60));
            }
        }
        for (PowerSource pSource : powerSources) {
            sb.append(String.format("%n %s @ %.1f%%", pSource.getName(), pSource.getTimeRemainingInstant() * 100d));
        }
        System.out.println(sb.toString());
    }

    private static void printDisks(List<HWDiskStore> diskStores) {
        System.out.println("Disks:");
        for (HWDiskStore disk : diskStores) {
            boolean readwrite = disk.getReads() > 0 || disk.getWrites() > 0;
            System.out.format(" %s: (model: %s - S/N: %s) size: %s, reads: %s (%s), writes: %s (%s), xfer: %s ms%n",
                    disk.getName(), disk.getModel(), disk.getSerial(),
                    disk.getSize() > 0 ? FormatUtil.formatBytesDecimal(disk.getSize()) : "?",
                    readwrite ? disk.getReads() : "?", readwrite ? FormatUtil.formatBytes(disk.getReadBytes()) : "?",
                    readwrite ? disk.getWrites() : "?", readwrite ? FormatUtil.formatBytes(disk.getWriteBytes()) : "?",
                    readwrite ? disk.getTransferTime() : "?");
            List<HWPartition> partitions = disk.getPartitions();
            if (partitions == null) {
                // TODO Remove when all OS's implemented
                continue;
            }
            for (HWPartition part : partitions) {
                System.out.format(" |-- %s: %s (%s) Maj:Min=%d:%d, size: %s%s%n", part.getIdentification(),
                        part.getName(), part.getType(), part.getMajor(), part.getMinor(),
                        FormatUtil.formatBytesDecimal(part.getSize()),
                        part.getMountPoint().isEmpty() ? "" : " @ " + part.getMountPoint());
            }
        }
    }

    private static void printFileSystem(FileSystem fileSystem) {
        System.out.println("File System:");

        System.out.format(" File Descriptors: %d/%d%n", fileSystem.getOpenFileDescriptors(),
                fileSystem.getMaxFileDescriptors());

        List<OSFileStore> fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray) {
            long usable = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            System.out.format(
                    " %s (%s) [%s] %s of %s free (%.1f%%) is %s "
                            + (fs.getLogicalVolume() != null && fs.getLogicalVolume().length() > 0 ? "[%s]" : "%s")
                            + " and is mounted at %s%n",
                    fs.getName(), fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), fs.getType(),
                    FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total,
                    fs.getVolume(), fs.getLogicalVolume(), fs.getMount());
        }
    }

    private static void printNetworkInterfaces(List<NetworkIF> networkIFs) {
        System.out.println("Network interfaces:");
        for (NetworkIF net : networkIFs) {
            System.out.format(" Name: %s (%s)%n", net.getName(), net.getDisplayName());
            System.out.format("   MAC Address: %s %n", net.getMacaddr());
            System.out.format("   MTU: %s, Speed: %s %n", net.getMTU(), FormatUtil.formatValue(net.getSpeed(), "bps"));
            System.out.format("   IPv4: %s %n", Arrays.toString(net.getIPv4addr()));
            System.out.format("   IPv6: %s %n", Arrays.toString(net.getIPv6addr()));
            boolean hasData = net.getBytesRecv() > 0 || net.getBytesSent() > 0 || net.getPacketsRecv() > 0
                    || net.getPacketsSent() > 0;
            System.out.format("   Traffic: received %s/%s%s; transmitted %s/%s%s %n",
                    hasData ? net.getPacketsRecv() + " packets" : "?",
                    hasData ? FormatUtil.formatBytes(net.getBytesRecv()) : "?",
                    hasData ? " (" + net.getInErrors() + " err)" : "",
                    hasData ? net.getPacketsSent() + " packets" : "?",
                    hasData ? FormatUtil.formatBytes(net.getBytesSent()) : "?",
                    hasData ? " (" + net.getOutErrors() + " err)" : "");
        }
    }

    private static void printNetworkParameters(NetworkParams networkParams) {
        System.out.println("Network parameters:");
        System.out.format(" Host name: %s%n", networkParams.getHostName());
        System.out.format(" Domain name: %s%n", networkParams.getDomainName());
        System.out.format(" DNS servers: %s%n", Arrays.toString(networkParams.getDnsServers()));
        System.out.format(" IPv4 Gateway: %s%n", networkParams.getIpv4DefaultGateway());
        System.out.format(" IPv6 Gateway: %s%n", networkParams.getIpv6DefaultGateway());
    }

    private static void printDisplays(List<Display> displays) {
        System.out.println("Displays:");
        int i = 0;
        for (Display display : displays) {
            System.out.println(" Display " + i + ":");
            System.out.println(display.toString());
            i++;
        }
    }

    private static void printUsbDevices(List<UsbDevice> usbDevices) {
        System.out.println("USB Devices:");
        for (UsbDevice usbDevice : usbDevices) {
            System.out.println(usbDevice.toString());
        }
    }
}
