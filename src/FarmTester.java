import java.util.ArrayList;
public class FarmTester {
    public static void main(String[] args) {
        int tests = 0;
        int failed = 0;
        Plot[][] testPlotArray = setupTestPlots();
        HappyFarm f = new HappyFarm(testPlotArray);

        System.out.println("--- TESTING PART (a) ---");
        Plot test1 = f.getTopYield("corn");
        if (test1 == testPlotArray[1][2]) {
            System.out.println("Test 1 Passed!");
            tests++;
        } else {
            System.out.println("***Test 1 FAILED*** wrong plot returned for the highest yield of corn");
            failed++;
        }
        Plot test2 = f.getTopYield("peas");
        if (test2 == testPlotArray[1][0] || test2 == testPlotArray[3][2]) {
            System.out.println("Test 2 Passed!");
            tests++;
        } else {
            System.out.println("***Test 2 FAILED*** wrong plot returned for the highest yield of peas");
            failed++;
        }
        Plot test3 = f.getTopYield("bananas");
        if (test3 == null) {
            System.out.println("Test 3 Passed!");
            tests++;
        } else {
            System.out.println("***Test 3 FAILED*** wrong plot returned for the highest yield of bananas");
            failed++;
        }
        System.out.println("------------------------");

        System.out.println("--- TESTING PART (b) ---");

        boolean test4 = f.identicalCrop(0);
        if (!test4) {
            System.out.println("Test 4 Passed!");
            tests++;
        } else {
            System.out.println("***Test 4 FAILED*** sameCrop(0) should return false, not true");
            failed++;
        }
        boolean test5 = f.identicalCrop(1);
        if (test5) {
            System.out.println("Test 5 Passed!");
            tests++;
        } else {
            System.out.println("***Test 5 FAILED*** sameCrop(1) should return true, not false");
            failed++;
        }
        System.out.println("------------------------");


        System.out.println("--- TESTING PART (c) ---");

        ArrayList<Plot> test6 = f.plotsContainingCrop("corn");
        if (test6.contains(testPlotArray[0][0]) && test6.contains(testPlotArray[0][1]) && test6.contains(testPlotArray[1][1]) &&
                test6.contains(testPlotArray[1][2]) && test6.contains(testPlotArray[2][1]) && test6.contains(testPlotArray[0][0]) &&
                test6.contains(testPlotArray[3][0]) && test6.contains(testPlotArray[3][1]) && test6.size() == 7) {
            System.out.println("Test 6 Passed!");
            tests++;
        } else {
            System.out.println("***Test 6 FAILED*** arrayList returned by plotsWithCrop(\"corn\") does not contain all plots of type corn");
            failed++;
        }
        ArrayList<Plot> test7 = f.plotsContainingCrop("wheat");
        if (test7.contains(testPlotArray[2][0]) && test7.size() == 1) {
            System.out.println("Test 7 Passed!");
            tests++;
        } else {
            System.out.println("***Test 7 FAILED*** arrayList returned by plotsWithCrop(\"wheat\") does not contain all plots of type wheat");
            failed++;
        }
        ArrayList<Plot> test8 = f.plotsContainingCrop("apples");
        if (test8.size() == 0) {
            System.out.println("Test 8 Passed!");
            tests++;
        } else {
            System.out.println("***Test 8 FAILED*** arrayList returned by plotsWithCrop(\"apples\") should be empty!");
            failed++;
        }
        System.out.println("------------------------");


        System.out.println("--- TESTING PART (d) ---");

        Plot p1 = new Plot("wheat", 10);
        Plot p2 = new Plot("corn", 50);
        Plot p3 = new Plot("peas", 10);
        Plot p4 = new Plot("rice", 30);
        Plot[] n1 = {p1, p2, p3, p4};
        f.widenFarm(n1);
        Plot[][] farm = f.getFarmPlots();

        if (farm.length == 4 && farm[0].length == 4) {
            System.out.println("Test 9-1 Passed!");
            tests++;
            if (farm[0][3] == p1 && farm[1][3] == p2 && farm[2][3] == p3 && farm[3][3] == p4) {
                System.out.println("Test 9-2 Passed!");
                tests++;
            } else {
                System.out.println("***Test 9-2 FAILED*** the 4 new plots were not inserted into a new column correctly");
                failed++;
            }
        } else {
            System.out.println("***Test 9 FAILED*** farmPlots should be 4x4, not " + farm.length + "x" + farm[0].length);
            failed++;
        }

        boolean passes = true;
        for (int r = 0; r < testPlotArray.length; r++) {
            for (int c = 0; c < testPlotArray[0].length; c++) {
                if (testPlotArray[r][c] != farm[r][c]) {
                    passes = false;
                }
            }
        }
        if (passes) {
            System.out.println("Test 10 Passed!");
            tests++;
        } else {
            System.out.println("***Test 10 FAILED*** farmPlots does not have the\n   same plots from the original copied over correctly");
            failed++;
        }

        HappyFarm f2 = new HappyFarm(testPlotArray);
        Plot p5 = new Plot("corn", 55);
        Plot[] n2 = {p1, p2, p3, p4, p5};
        f2.widenFarm(n2);
        Plot[][] farm2 = f2.getFarmPlots();

        if (farm2.length == 5 && farm2[0].length == 4) {
            System.out.println("Test 11-1 Passed!");
            tests++;
            if (farm2[0][3] == p1 && farm2[1][3] == p2 && farm2[2][3] == p3 && farm2[3][3] == p4 && farm2[4][3] == p5) {
                System.out.println("Test 11-2 Passed!");
                tests++;
            } else {
                System.out.println("***Test 11-2 FAILED*** the 5 new plots were not inserted into a new column correctly");
                failed++;
            }
            if (farm2[4][0] != null && farm2[4][1] != null && farm2[4][2] != null && farm2[4][3] != null) {
                System.out.println("Test 11-3 Passed!");
                tests++;
                if (farm2[4][0].getCropType().equals("soil") && farm2[4][1].getCropType().equals("soil")
                        && farm2[4][2].getCropType().equals("soil") && farm2[4][0].getCropYield() == 0
                        && farm2[4][1].getCropYield() == 0 && farm2[4][2].getCropYield() == 0) {
                    System.out.println("Test 11-4 Passed!");
                    tests++;
                } else {
                    System.out.println("***Test 11-4 FAILED*** there should be a 4th row with\n   'soil' and '0' as crop type and yield as the first elements in the row");
                }
            } else {
                System.out.println("***Test 11-3 FAILED*** one or more of the plots in your new row are null rather than a new Plot of type 'soil' with yield 0");
                failed++;
            }
        } else {
            System.out.println("***Test 11 FAILED*** farmPlots should be 5x4, not " + farm2.length + "x" + farm2[0].length);
            failed++;
        }

        boolean passes2 = true;
        for (int r = 0; r < testPlotArray.length; r++) {
            for (int c = 0; c < testPlotArray[0].length; c++) {
                if (testPlotArray[r][c] != farm2[r][c]) {
                    passes2 = false;
                }
            }
        }
        if (passes2) {
            System.out.println("Test 12 Passed!");
            tests++;
        } else {
            System.out.println("***Test 12 FAILED*** farmPlots does not have the\n   same plots from the original copied over correctly");
            failed++;
        }

        HappyFarm f3 = new HappyFarm(testPlotArray);
        Plot[] n3 = {p1, p2};
        f3.widenFarm(n3);
        Plot[][] farm3 = f3.getFarmPlots();

        if (farm3.length == 4 && farm3[0].length == 4) {
            System.out.println("Test 13-1 Passed!");
            tests++;
            if (farm3[0][3] == p1 && farm3[1][3] == p2) {
                System.out.println("Test 13-2 Passed!");
                tests++;
            } else {
                System.out.println("***Test 13-2 FAILED*** the 2 new plots were not inserted into a new column correctly");
                failed++;
            }

            if (farm3[2][3].getCropType().equals("soil") && farm3[3][3].getCropType().equals("soil")
                    && farm3[2][3].getCropYield() == 0 && farm3[3][3].getCropYield() == 0) {
                System.out.println("Test 13-3 Passed!");
                tests++;
            } else {
                System.out.println("***Test 13-3 FAILED*** there should be two elements in the right column\n   with 'soil' and '0' as crop type and yield as the first elements in the row");
            }

        } else {
            System.out.println("***Test 13 FAILED*** farmPlots should be 4x4, not " + farm2.length + "x" + farm2[0].length);
            failed++;
        }

        boolean passes3= true;
        for (int r = 0; r < testPlotArray.length; r++) {
            for (int c = 0; c < testPlotArray[0].length; c++) {
                if (testPlotArray[r][c] != farm3[r][c]) {
                    passes3 = false;
                }
            }
        }
        if (passes3) {
            System.out.println("Test 14 Passed!");
            tests++;
        } else {
            System.out.println("***Test 14 FAILED*** farmPlots does not have the\n   same plots from the original copied over correctly");
            failed++;
        }
        System.out.println("------------------------");
        System.out.println("TOTAL TESTS SKIPPED due to other failed tests: " + (20 - (tests + failed)));

        System.out.println("TOTAL TESTS PASSED: " + tests);
        System.out.println("TOTAL TESTS FAILED: " + failed);
    }

    // sets up the plots to reflect sample in handout
    public static Plot[][] setupTestPlots() {
        Plot p1 = new Plot("corn", 20);
        Plot p2 = new Plot("corn", 30);
        Plot p3 = new Plot("peas", 10);
        Plot p4 = new Plot("peas", 30);
        Plot p5 = new Plot("corn", 40);
        Plot p6 = new Plot("corn", 62);
        Plot p7 = new Plot("wheat", 10);
        Plot p8 = new Plot("corn", 50);
        Plot p9 = new Plot("rice", 30);
        Plot p10 = new Plot("corn", 55);
        Plot p11 = new Plot("corn", 30);
        Plot p12 = new Plot("peas", 30);

        Plot[][] testArr = {{p1, p2, p3},
                {p4, p5, p6},
                {p7, p8, p9},
                {p10, p11, p12}};
        return testArr;
    }
}
