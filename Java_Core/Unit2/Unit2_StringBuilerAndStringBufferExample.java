class ConcatenateStringTest {
    public static void main(String[] args) {
        int max = 10000;
        StringBuilder builder = new StringBuilder();
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            builder.append(String.valueOf(i));
        }
        System.out.println("time 1= " + (System.currentTimeMillis() - start));
        StringBuffer buffer = new StringBuffer();
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            buffer.append(String.valueOf(i));
        }
        System.out.println("Time 2= " + (System.currentTimeMillis() - start1));
        String text = "";
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            text += String.valueOf(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time 3 = " + (end - start));

    }
}