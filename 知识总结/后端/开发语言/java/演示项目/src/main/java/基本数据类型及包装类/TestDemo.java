package 基本数据类型及包装类;

public class TestDemo {
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.setC(1);
        int c = demo1.getC();
        Demo2 demo2 = new Demo2();
        demo2.setC(2);
        Integer c1 = demo2.getC();
        c = c1;
        System.out.println(c1);
    }
}
