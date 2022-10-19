public class Unit9_Lambda_Runnable {
    public static void main(String[] args) {
        /* C1:
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" say hello Java class");
            }
        };
        new Thread(runnable).start();
        */
        /*c2:
        new Thread(() -> System.out.println(Thread.currentThread()
                .getName()+" say hello java")).start();

         */
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+" say hello java");
            System.out.println("1+1= "+(1+1));
        }).start();
    }


}