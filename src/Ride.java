public class Ride  {
    public int id;

    public Pair<Integer, Integer> start;
    public Pair<Integer, Integer> end;

    public int earliestStart;
    public int latestFinish;

    public Ride(int id, int startX, int startY, int endX, int endY, int earliest, int latest) {
        this(id, new Pair<Integer, Integer>(startX, startY), new Pair<Integer, Integer>(endX, endY), earliest, latest);
    }

    public Ride (int id, Pair<Integer, Integer> start, Pair<Integer, Integer> end, int earliest, int latest) {
        this.id = id;

        this.start = start;
        this.end = end;

        this.earliestStart = earliest;
        this.latestFinish = latest;
    }

    public int distance() {
        return (start.getLeft() - end.getLeft()) + (start.getRight() - end.getRight());
    }

    public int distance(Vehicle v) {
        return (v.currentX - start.getLeft()) + (v.currentY - start.getRight());
    }
}