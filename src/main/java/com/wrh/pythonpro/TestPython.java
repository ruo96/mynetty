package com.wrh.pythonpro;

import org.junit.Test;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * @author : wuruohong
 * @description :
 * @Date : 2023/2/9 11:20
 */
public class TestPython {



    public static void main(String[] args) {
        try {
//            String pythonCode = "print(\"Hello Python!\")";
            String pythonCode = "a = 1";
            Process proc = Runtime.getRuntime().exec("python -c \"" + pythonCode + "\"");
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (Exception e) {

        }
    }

    @Test
    public void Test31() {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("a = 1");
        interpreter.exec("print a");
        interpreter.exec("print(\"Hello Python!\")");
        String pythonCode = "print(\"Hello world!\")";
        interpreter.exec(pythonCode);



    }

    @Test
    public void Test47() {
        try {
            String pythonCode = "print(\"Hello Python!\")";
//            String pythonCode = "a = 1";
            Process proc = Runtime.getRuntime().exec("python -c \"" + pythonCode + "\"");
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

        } catch (Exception e) {

        }

    }

}
