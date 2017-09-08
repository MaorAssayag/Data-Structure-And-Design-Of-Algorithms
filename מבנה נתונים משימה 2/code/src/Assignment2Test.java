
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 *
 * @author evyatar
 */
public class Assignment2Test {

    private int maxDifferentTests = 100000; //how many different fields of points to test
    private int maxPoints = 500;

    public static void main(String[] args) {
        Assignment2Test test = new Assignment2Test();
        test.EdgeCasesTest();
        test.autoTests();
        //use this if you want to save the last test to code
        //test.saveTestToCode();
    }

    public Assignment2Test() {
        this(false);
    }

    public Assignment2Test(boolean replaceSavedTest) {
        initLogger();
        foundError = true;
        if (replaceSavedTest || !loadTestFile()) {
            initializeNewTest();
        }
    }

    private void initLogger() {
        testLogger.setLevel(Level.SEVERE);
        testLogger.setUseParentHandlers(false);
        MyFormatter formatter = new MyFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
        testLogger.addHandler(handler);
    }

    public void EdgeCasesTest() {
        DataStructure dTrivial = new DataStructure();
        assert (dTrivial.getMedian(true) == null);
        assert (dTrivial.getMedian(false) == null);
        assert (dTrivial.getPointsInRangeRegAxis(0, 1, false).length == 0);
        assert (dTrivial.getPointsInRangeRegAxis(0, 1, true).length == 0);
        assert (dTrivial.nearestPair() == null || dTrivial.nearestPair().length == 0);
        Point p = new Point(10, 10);
        dTrivial.addPoint(p);
        assert (dTrivial.nearestPair() == null || dTrivial.nearestPair().length == 0);
    }

    public void setMaxDifferentTests(int num) {
        maxDifferentTests = num;
    }

    public void saveTestToCode() {
        saveTestToCode("lastTest");
    }

    public void saveTestToCode(String fileName) {
        File readableFile = new File(desktop + "/Assignment2Tests", fileName + ".txt");
        try {
            readableFile.createNewFile();
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(readableFile)));
            writer.println("DataStructure testDt = new DataStructure();");
            for (int i = 0; i < size; i++) {
                writer.println("testDt.addPoint(new Point(" + arraySortedX[i].getX() + "," + arraySortedX[i].getY() + "));");
            }
            writer.println("testDt.getPointsInRangeRegAxis(" + minMax[0][0] + "," + minMax[0][1] + ", true)");
            writer.println("testDt.getPointsInRangeRegAxis(" + minMax[1][0] + "," + minMax[1][1] + ", false)");
            writer.println("testDt.getPointsInRangeOppAxis(" + minMax[2][0] + "," + minMax[2][1] + ", true)");
            writer.println("testDt.getPointsInRangeOppAxis(" + minMax[3][0] + "," + minMax[3][1] + ", false)");
            writer.println("testDt.narrowRange(" + minMax[4][0] + "," + minMax[4][1] + ", true)");
            writer.println("testDt.narrowRange(" + minMax[5][0] + "," + minMax[5][1] + ", false)");
            writer.close();
            testLogger.info("Test code was saved successfully to: " + pointsFile.getCanonicalPath());
        } catch (Exception e) {
            testLogger.warning("Failed to save test code file");
        }

    }

    public void autoTests() {
        int k = 1000;
        for (int i = 1; i <= maxDifferentTests; i++) {
            if (i / k > 0) {
                System.out.println("Passed " + k + " tests");
                k += 1000;
            }
            try {
                if (!autoTestWithValues()) {
                    saveValues();
                    savePointsToFile();
                    return;
                }
                initializeNewTest();
            } catch(Exception e){
                saveValues();
                savePointsToFile();
                e.printStackTrace();
                return;
            }
        }
    }

    public boolean foundError() {
        return foundError;
    }

    public void setDetailedLogging(boolean state) {
        testLogger.setLevel(state ? Level.ALL : Level.SEVERE);
    }

    public boolean autoTestWithValues() {
        testLogger.info("Starting auto testing");
        foundError = !testGetDensity()
                || !testGetLargestAxis()
                || !testGetMedian(true)
                || !testGetMedian(false)
                || !testNearestPair()
                || !testGetPointsInRangeRegAxisX(minMax[0][0], minMax[0][1])
                || !testGetPointsInRangeRegAxisY(minMax[1][0], minMax[1][1])
                || !testGetPointsInRangeOppAxisX(minMax[2][0], minMax[2][1])
                || !testGetPointsInRangeOppAxisY(minMax[3][0], minMax[3][1])
                || !testNarrowRangeX(minMax[4][0], minMax[4][1])
                || !testNarrowRangeY(minMax[5][0], minMax[5][1]);
        if (foundError) {
            return false;
        }
        testLogger.info("Finished auto test");
        return true;
    }

    private void initMinMax() {
        Random rand = new Random();
        minMax = new int[6][2];
        for (int i = 0; i < 6; i++) {
            int temp1 = rand.nextInt(maxPoints);
            int temp2 = rand.nextInt(maxPoints);
            minMax[i][0] = Math.min(temp1, temp2);
            minMax[i][1] = Math.max(temp1, temp2);
        }
    }

    private void saveValues() {
        testLogger.info("Trying to save values file....");
        try {
            valuesFile.getParentFile().mkdir();
            valuesFile.createNewFile();
            FileOutputStream fileOut
                    = new FileOutputStream(valuesFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 2; j++) {
                    out.writeInt(minMax[i][j]);
                }
            }
            out.close();
            fileOut.close();
            testLogger.info("Values file was saved successfully to: " + valuesFile.getAbsolutePath());
        } catch (Exception e) {
            testLogger.warning("Failed to save values file");
        }
    }

    public final void initializeNewTest() {
        initMinMax();
        testLogger.info("Initializing new test");
        boolean[] Xnums = new boolean[maxPoints*10+1];
        boolean[] Ynums = new boolean[maxPoints*10+1];
        Random rand = new Random();
        size = rand.nextInt(maxPoints - 2) + 2;
        testLogger.info("Number of points is: " + size);
        arraySortedX = new Point[size];
        arraySortedY = new Point[size];
        int i = 0;
        int x;
        int y;
        Point p;
        while (i < size) {
            x = rand.nextInt(maxPoints * 10);
            y = rand.nextInt(maxPoints * 10);
            while (Xnums[x]) {
                x = rand.nextInt(maxPoints * 10);
            }
            Xnums[x]=true;
            while (Ynums[y]) {
                y = rand.nextInt(maxPoints * 10);
            }
            Ynums[y]=true;
            p = new Point(x, y);
            arraySortedX[i] = p;
            arraySortedY[i] = p;
            i++;
        }
        initDt();
        arraySortedY = arraySortedX.clone();
        Arrays.sort(arraySortedY, new forSortY());
        Arrays.sort(arraySortedX, new forSortX());
        testLogger.info("Finished initializing new test");
    }

    public final boolean loadTestFile() {
        return loadValuesFile() && loadPointsFile();
    }

    public boolean loadValuesFile() {
        testLogger.info("Trying to load values file....");
        try {
            FileInputStream fileIn = new FileInputStream(valuesFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            minMax = new int[6][2];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 2; j++) {
                    minMax[i][j] = in.readInt();
                }
            }
            in.close();
            fileIn.close();
            testLogger.info("Loaded values file successfully");
            return true;
        } catch (Exception e) {
            testLogger.warning("Failed to load values file");
            //e.printStackTrace();
            return false;
        }
    }

    public boolean loadPointsFile() {
        testLogger.info("Trying to load test file....");
        try {
            FileInputStream fileIn = new FileInputStream(pointsFile);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            size = in.readInt();
            arraySortedX = new Point[size];
            Point p;
            int x, y;
            for (int i = 0; i < size; i++) {
                x = in.readInt();
                y = in.readInt();
                p = new Point(x, y);
                arraySortedX[i] = p;
            }
            in.close();
            fileIn.close();
            size = arraySortedX.length;
            arraySortedY = arraySortedX.clone();
            initDt();
            Arrays.sort(arraySortedY, new forSortY());
            //Arrays.sort(arraySortedX, new forSortX());
            testLogger.info("Loaded test file successfully");
            return true;
        } catch (Exception e) {
            testLogger.warning("Failed to load test file");
            return false;
        }
    }

    private void initDt() {
        dt = new DataStructure();
        for (int i = 0; i < arraySortedX.length; i++) {
            dt.addPoint(arraySortedX[i]);
        }
    }

    public final void savePointsToFile() {
        testLogger.info("Trying to save points file....");
        try {
            pointsFile.getParentFile().mkdir();
            pointsFile.createNewFile();
            FileOutputStream fileOut
                    = new FileOutputStream(pointsFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeInt(size);
            for (int i = 0; i < size; i++) {
                out.writeInt(arraySortedX[i].getX());
                out.writeInt(arraySortedX[i].getY());
            }
            out.close();
            fileOut.close();
            testLogger.info("Points file was saved successfully to: " + pointsFile.getAbsolutePath());
        } catch (Exception e) {
            testLogger.warning("Failed to save points file");
        }
    }

    public boolean testGetPointsInRangeRegAxisX(int min, int max) {
        testLogger.info("Testing getPointsInRangeRegAxis by X, max: " + max + " min: " + min);
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedX[i].getX() >= min & arraySortedX[i].getX() <= max) {
                count++;
            }
        }
        Point[] expected = new Point[count];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedX[i].getX() >= min & arraySortedX[i].getX() <= max) {
                expected[k] = arraySortedX[i];
                k++;
            }
        }
        Point[] result = dt.getPointsInRangeRegAxis(min, max, true);
        if (checkEquality(result, expected)) {
            testLogger.info("Finished GetPointsInRangeRegAxisX test successfully");
            return true;
        } else {
            testLogger.severe("Failed GetPointsInRangeRegAxisX test, max: " + max + " min: " + min);
            return false;
        }
    }

    public boolean testGetPointsInRangeRegAxisY(int min, int max) {
        testLogger.info("Testing getPointsInRangeRegAxis by Y, max: " + max + " min: " + min);
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedY[i].getY() >= min & arraySortedY[i].getY() <= max) {
                count++;
            }
        }
        Point[] expected = new Point[count];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedY[i].getY() >= min & arraySortedY[i].getY() <= max) {
                expected[k] = arraySortedY[i];
                k++;
            }
        }
        Point[] result = dt.getPointsInRangeRegAxis(min, max, false);
        if (checkEquality(result, expected)) {
            testLogger.info("Finished GetPointsInRangeRegAxisY test successfully");
            return true;
        } else {
            testLogger.severe("Failed GetPointsInRangeRegAxisY test, max: " + max + " min: " + min);
            return false;
        }
    }

    public boolean testGetPointsInRangeOppAxisX(int min, int max) {
        testLogger.info("Testing GetPointsInRangeOppAxis by X, max:" + max + " min:" + min);
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedX[i].getX() >= min & arraySortedX[i].getX() <= max) {
                count++;
            }
        }
        Point[] expected = new Point[count];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedY[i].getX() >= min & arraySortedY[i].getX() <= max) {
                expected[k] = arraySortedY[i];
                k++;
            }
        }
        Point[] result = dt.getPointsInRangeOppAxis(min, max, true);
        if (checkEquality(result, expected)) {
            testLogger.info("Finished GetPointsInRangeOppAxisX test successfully");
            return true;
        } else {
            testLogger.severe("Failed GetPointsInRangeOppAxisX test, max:" + max + " min:" + min);
            return false;
        }
    }

    public boolean testGetPointsInRangeOppAxisY(int min, int max) {
        testLogger.info("Testing GetPointsInRangeOppAxis by Y, max:" + max + " min:" + min);
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedY[i].getY() >= min & arraySortedY[i].getY() <= max) {
                count++;
            }
        }
        Point[] expected = new Point[count];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedX[i].getY() >= min & arraySortedX[i].getY() <= max) {
                expected[k] = arraySortedX[i];
                k++;
            }
        }
        Point[] result = dt.getPointsInRangeOppAxis(min, max, false);
        if (checkEquality(result, expected)) {
            testLogger.info("Finished GetPointsInRangeOppAxisY test successfully");
            return true;
        } else {
            testLogger.severe("Failed GetPointsInRangeOppAxisY test");
            return false;
        }
    }

    public boolean testGetDensity() {
        testLogger.info("Testing density");
        double density = dt.getDensity();
        double expectedDensity = size >= 2 ? (((double) size) / ((arraySortedX[size - 1].getX() - arraySortedX[0].getX())
                * (arraySortedY[size - 1].getY() - arraySortedY[0].getY()))) : 0;
        if (((int) density) != ((int) expectedDensity)) {
            testLogger.severe("Failed density test, expected: " + expectedDensity + " got: " + density);
            return false;
        } else {
            testLogger.info("Finished GetDensity test");
            return true;
        }
    }

    public boolean testNarrowRangeX(int min, int max) {
        testLogger.info("Testing narrowRange by X, max:" + max + " min:" + min);
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedX[i].getX() >= min & arraySortedX[i].getX() <= max) {
                count++;
            }
        }
        Point[] expected = new Point[count];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedX[i].getX() >= min & arraySortedX[i].getX() <= max) {
                expected[k] = arraySortedX[i];
                k++;
            }
        }
        dt.narrowRange(min, max, true);
        Point[] result = dt.getPointsInRangeRegAxis(Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, true);
        initDt();
        if (checkEquality(result, expected)) {
            testLogger.info("Finished NarrowRangeX test successfully");
            return true;
        } else {
            testLogger.severe("Failed NarrowRangeX test");
            return false;
        }
    }

    private boolean checkEquality(Point[] result, Point[] expected) {
        if (result.length != expected.length) {
            testLogger.severe("Failed. expected size, got: " + result.length
                    + " expected: " + expected.length);
            return false;
        }
        for (int i = 0; i < result.length; i++) {
            if (!result[i].equals(expected[i])) {
                testLogger.severe("Failed. at index: " + i + " got point: " + result[i].toString()
                        + "\n expected point " + expected[i].toString());
                return false;
            }
        }
        return true;
    }

    public boolean testNarrowRangeY(int min, int max) {
        testLogger.info("Testing narrowRange by Y, max:" + max + " min:" + min);
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedY[i].getY() >= min & arraySortedY[i].getY() <= max) {
                count++;
            }
        }
        Point[] expected = new Point[count];
        int k = 0;
        for (int i = 0; i < size; i++) {
            if (arraySortedY[i].getY() >= min & arraySortedY[i].getY() <= max) {
                expected[k] = arraySortedY[i];
                k++;
            }
        }
        dt.narrowRange(min, max, false);
        Point[] result = dt.getPointsInRangeRegAxis(Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, false);
        initDt();
        if (checkEquality(result, expected)) {
            testLogger.info("Finished NarrowRangeX test successfully");
            return true;
        } else {
            testLogger.severe("Failed NarrowRangeY test, max:" + max + " min:" + min);
            return false;
        }
    }

    public boolean testGetLargestAxis() {
        testLogger.info("Testing GetLargestAxis");
        if (size < 1) {
            return !dt.getLargestAxis();
        }
        boolean expected = arraySortedX[size - 1].getX() - arraySortedX[0].getX()
                > arraySortedY[size - 1].getY() - arraySortedY[0].getY();
        if (expected != dt.getLargestAxis()) {
            testLogger.severe("Failed, got: " + dt.getLargestAxis() + " expected: " + expected);
            return false;
        } else {
            testLogger.info("Finished GetLargestAxis test successfully");
            return true;
        }
    }

    public boolean testGetMedian(boolean axis) {
        testLogger.info("Testing GetMedian, axis: " + axis);
        if (size < 1) {
            return dt.getMedian(axis) == null;
        }
        Point result = dt.getMedian(axis).getData();
        Point expected = axis ? arraySortedX[size / 2] : arraySortedY[size / 2];
        if (!result.equals(expected)) {
            testLogger.severe("Failed GetMedian axis:" + axis + ", got: " + result.toString() + " expected: " + expected.toString());
            return false;
        } else {
            testLogger.info("Finished GetMedian test successfully");
            return true;
        }
    }

    private int get(Point p, boolean axis) {
        int result = axis ? p.getX() : p.getY();
        return result;
    }

    private Point[] closestPair(Point[] arr) {
        Point[] result = new Point[2];
        double minDistance = Double.MAX_VALUE, newDist = 0;
        double oldDist = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                newDist = almostDist(arr[i], arr[j]);
                if (newDist < oldDist) {
                    result[0] = arr[i];
                    result[1] = arr[j];
                    minDistance = distanceCalc(arr[i], arr[j]);
                    oldDist=newDist;
                }
            }
        }
        return result;
    }

    private static double distanceCalc(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    private static double almostDist(Point p1, Point p2) {
        return (p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY());
    }

    public boolean testNearestPair() {
        testLogger.info("Testing NearestPair");
        Point[] expectedPair = closestPair(arraySortedX);
        Point[] result = dt.nearestPair();
        if (distanceCalc(result[0], result[1]) !=  distanceCalc(expectedPair[0], expectedPair[1])) {
            testLogger.severe("Failed NearestPair, got points: " + result[0].toString() + " and " + result[1].toString()
                    + " ,expected: " + expectedPair[0].toString() + " and " + expectedPair[1].toString());
            return false;
        } else {
            testLogger.info("Finished NearestPairInStrip test successfully");
            return true;
        }
    }

    class forSortX implements Comparator<Point> {

        public int compare(Point p1, Point p2) {
            return Integer.compare(p1.getX(), p2.getX());
        }
    }

    class forSortY implements Comparator<Point> {

        public int compare(Point p1, Point p2) {
            return Integer.compare(p1.getY(), p2.getY());
        }
    }

    class MyFormatter extends Formatter {

        public String format(LogRecord record) {
            StringBuilder builder = new StringBuilder(1000);
            builder.append("[").append(record.getSourceClassName()).append(".");
            builder.append(record.getSourceMethodName()).append("] - ");
            builder.append("[").append(record.getLevel()).append("] - ");
            builder.append(formatMessage(record));
            builder.append("\n");
            return builder.toString();
        }

        public String getHead(Handler h) {
            return super.getHead(h);
        }

        public String getTail(Handler h) {
            return super.getTail(h);
        }
    }

    private File desktop = new File(System.getProperty("user.home"), "Desktop");
    private File pointsFile = new File(desktop + "/Assignment2Tests", "savedPoints.evya");
    private File valuesFile = new File(desktop + "/Assignment2Tests", "savedValues.evya");
    private static final Logger testLogger = Logger.getLogger("testLogger");
    private DataStructure dt;
    private Point[] arraySortedX;
    private Point[] arraySortedY;
    private int size;
    private boolean foundError;
    private int[][] minMax;
}
