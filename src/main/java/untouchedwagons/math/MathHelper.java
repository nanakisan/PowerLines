package untouchedwagons.math;

public class MathHelper
{
    private MathHelper()
    {

    }

    /**
     * Calculates the angle between two points in 2D space
     * @param a1
     * @param b1
     * @param a2
     * @param b2
     * @return
     */
    public static double calculateAngle(int a1, int b1, int a2, int b2)
    {
        double distance_between_a1_a2 = calculateDifference(a1, a2);
        double distance_between_b1_b2 = calculateDifference(b1, b2);

        return Math.atan(distance_between_b1_b2 / distance_between_a1_a2);
    }

    /**
     * Calculates the angle between two points in 3D space
     * @param x1
     * @param y1
     * @param z1
     * @param x2
     * @param y2
     * @param z2
     * @return
     */
    public static double calculateAngle (int x1, int y1, int z1, int x2, int y2, int z2)
    {
        double distance_between_x1z1_x2z2 = calculateDistance(x1, z1, x2, z2);
        double distance_between_y1_y2 = calculateDifference(y1, y2);

        return Math.atan(distance_between_y1_y2 / distance_between_x1z1_x2z2);
    }

    /**
     * This method calculated the distance between two points in 3D space
     * @param x1 X1
     * @param y1 Y1
     * @param z1 Z1
     * @param x2 X2
     * @param y2 Y2
     * @param z2 Z2
     * @return The distance between the two points
     */
    public static double calculateDistance(int x1, int y1, int z1, int x2, int y2, int z2)
    {
        double x_distance = x1 - x2;
        double y_distance = y1 - y2;
        double z_distance = z1 - z2;

        double sum = x_distance * x_distance;
        sum += y_distance * y_distance;
        sum += z_distance * z_distance;

        return Math.sqrt(sum);
    }

    /**
     * This method calculated the distance between two points in 3D space
     * @param a1
     * @param b1
     * @param a2
     * @param b2
     * @return
     */
    public static double calculateDistance(int a1, int b1, int a2, int b2)
    {
        double a_distance = a1 - a2;
        double b_distance = b1 - b2;

        double sum = a_distance * a_distance;
        sum += b_distance * b_distance;

        return Math.sqrt(sum);
    }

    /**
     * Calculates the absolute distance between two values
     * @param v1
     * @param v2
     * @return
     */
    public static double calculateDifference(int v1, int v2) {
        return v1 < v2 ? v2 - v1 : v1 - v2;
    }
}
