Cglib是以动态生成的子类继承目标的方式实现，在运行期动态的在内存中构建一个子类，如下:
public class UserDao{}
//Cglib是以动态生成的子类继承目标的方式实现，程序执行时，隐藏了下面的过程
public class $Cglib_Proxy_class  extends UserDao{}
Cglib使用的前提是目标类不能为final修饰。因为final修饰的类不能被继承。