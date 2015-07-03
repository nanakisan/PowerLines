package untouchedwagons.math;

public class MathHelper
{
    private MathHelper()
    {

    }

    /**
     * This method calculated the distance between two points in three dimensional space
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
        boolean shared_x_axis = x1 == x2;
        boolean shared_y_axis = y1 == y2;
        boolean shared_z_axis = z1 == z2;

        int shared_axis_count = (shared_x_axis ? 1 : 0) +
                                (shared_y_axis ? 1 : 0) +
                                (shared_z_axis ? 1 : 0);

        if (shared_axis_count == 2)
        {
            if (!shared_x_axis)
                return calculateDifference(x1, x2);
            if (!shared_y_axis)
                return calculateDifference(y1, y2);
            if (!shared_z_axis)
                return calculateDifference(z1, z2);
        }
        else if (shared_axis_count == 1)
        {
            if (shared_x_axis)
                return calculateDistance(y1, z1, y2, z2);
            if (shared_y_axis)
                return calculateDistance(x1, z1, x2, z2);
            if (shared_z_axis)
                return calculateDistance(x1, y1, x2, y2);
        }

        double x1z1_x2z2_distance = calculateDistance(x1, y1, x2, y2);

        double distance_between_y1_y2 = calculateDifference(y1, y2);

        double angle = Math.atan(distance_between_y1_y2 / x1z1_x2z2_distance);

        return x1z1_x2z2_distance / Math.cos(angle);
    }

    public static double calculateDistance(int a1, int b1, int a2, int b2)
    {
        double distance_between_a1_a2 = calculateDifference(a1, a2);
        double distance_between_b1_b2 = calculateDifference(b1, b2);

        double a1b1_a2b2_angle = Math.atan(distance_between_b1_b2 / distance_between_a1_a2);

        return distance_between_a1_a2 / Math.cos(a1b1_a2b2_angle);
    }

    public static double calculateDifference(int v1, int v2) {
        return v1 < v2 ? v2 - v1 : v1 - v2;
    }
}
