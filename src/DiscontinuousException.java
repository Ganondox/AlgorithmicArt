public class DiscontinuousException extends Exception {

    int failPoint;
    int r0;
    int r1;
    int g0;
    int g1;
    int b0;
    int b1;

    public DiscontinuousException( int failPoint) {
        this.failPoint = failPoint;
    }

    public DiscontinuousException(int failPoint, int r0, int r1, int g0, int g1, int b0, int b1) {
        this.failPoint = failPoint;
        this.r0 = r0;
        this.r1 = r1;
        this.g0 = g0;
        this.g1 = g1;
        this.b0 = b0;
        this.b1 = b1;
    }
}
