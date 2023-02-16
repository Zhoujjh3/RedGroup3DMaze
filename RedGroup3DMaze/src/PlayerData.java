public class PlayerData {
    private int[] coordinates = new int[3];
    private char direction;
    private int score;
    public PlayerData(Maze maze) {
        coordinates[0] = 0;
        coordinates[1] = 0;
        coordinates[2] = 3;
        score = 0;
    }

    public int getCoordinate(char value) {
        return coordinates[1];
    }
    public char getDirection() {
        return direction;
    }

//<orderEntry type="jdk" jdkName="Android API 29 Platform" jdkType="Android SDK" />
    public int getScore() {
        return score;
    }
}
