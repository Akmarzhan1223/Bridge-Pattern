public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo();
        } else {
            System.out.println("Usage: java -cp out Main --demo");
        }
    }

    static void runDemo() {
        int pass = 0;
        int total = 5;

        // T1: A1 + I1
        Shape circle1 = new Circle("C1", 2, new VectorRenderer());
        String t1Actual = circle1.execute();
        String t1Expected = "VECTOR circle radius=2.0";
        boolean t1 = t1Actual.equals(t1Expected);
        printResult("T1", t1, "Circle + VectorRenderer", t1Actual, t1Expected);

        // T2: A1 + I2
        Shape circle2 = new Circle("C2", 2, new RasterRenderer());
        String t2Actual = circle2.execute();
        String t2Expected = "RASTER circle radius=2.0";
        boolean t2 = t2Actual.equals(t2Expected);
        printResult("T2", t2, "Circle + RasterRenderer", t2Actual, t2Expected);

        // T3: A2 + I1
        Shape square1 = new Square("S1", 3, new VectorRenderer());
        String t3Actual = square1.execute();
        String t3Expected = "VECTOR square side=3.0";
        boolean t3 = t3Actual.equals(t3Expected);
        printResult("T3", t3, "Square + VectorRenderer", t3Actual, t3Expected);

        // T4: A2 + I2
        Shape square2 = new Square("S2", 3, new RasterRenderer());
        String t4Actual = square2.execute();
        String t4Expected = "RASTER square side=3.0";
        boolean t4 = t4Actual.equals(t4Expected);
        printResult("T4", t4, "Square + RasterRenderer", t4Actual, t4Expected);

        // T5: смена рендерера на лету
        Circle original = new Circle("C5", 2, new VectorRenderer());
        Shape refBefore = original;
        String beforeResult = refBefore.execute();
        String beforeId = refBefore.getId();

        refBefore.setImplementation(new RasterRenderer());
        String afterResult = refBefore.execute();

        boolean sameObject = (refBefore == original);
        boolean idUnchanged = refBefore.getId().equals(beforeId);
        boolean t5 = sameObject && idUnchanged
                && beforeResult.equals("VECTOR circle radius=2.0")
                && afterResult.equals("RASTER circle radius=2.0");

        System.out.println("T5 " + (t5 ? "PASS" : "FAIL")
                + " | sameObject=" + sameObject
                + " | stateUnchanged=" + idUnchanged
                + " before=" + beforeResult
                + " | after=" + afterResult);



        if (t1) pass++;
        if (t2) pass++;
        if (t3) pass++;
        if (t4) pass++;
        if (t5) pass++;


        System.out.println("SUMMARY: " + pass + "/" + total + " PASS");
    }

    static void printResult(String id, boolean pass, String classes,
                            String actual, String expected) {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(pass ? " PASS" : " FAIL");
        sb.append(" | ").append(classes);
        sb.append(" | result=").append(actual);
        if (!pass) {
            sb.append(" | expected=").append(expected);
        }
        System.out.println(sb.toString());
    }
}