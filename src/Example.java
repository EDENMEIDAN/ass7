import task.Task;

public class Example implements Task<Integer>  {

    public void foo() {

        final String a = "this";

        Task<Integer> t = new Task<Integer>() {

            String b = "that";
            Integer number = 20;

            public Integer run() {
                System.out.println("The message is:" + a + " " + this.b);
                this.number++;
                return this.number;
            }
        };

        int result = 0;
        for (int i = 0; i < 5; i++) {
            result += t.run();
        }
        System.out.println("result:" + result);
    }

    public static void main(String[] args) {
        Example example = new Exapmle();
        example.foo();
    }

    /**
     * this method runs the general task.
     *
     * @return unimplemented option.
     */
    @Override
    public Integer run() { //todo
        return null;
    }
}