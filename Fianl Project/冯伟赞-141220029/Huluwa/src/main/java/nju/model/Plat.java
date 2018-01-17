package nju.model;

public class Plat {

    private int height;
    private int width;
    private int[][] data;

    public Plat() {
    }

    public int getHeight() {
        return Math.min(height, data.length);
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        if (data.length == 0) {
            return 0;
        }
        return Math.min(width, data[0].length);
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[][] getData() {
        return data;
    }

    public int getDataValue(int row, int col) {
        return data[row][col];
    }

    public void setData(int[][] data) {
        this.data = data;
    }

    public void setDataValue(int row, int col, int value) {
        data[row][col] = value;
    }

    public void swap(int row1, int col1, int row2, int col2) {
        int temp = data[row1][col1];
        data[row1][col1] = data[row2][col2];
        data[row2][col2] = temp;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append("" + data[i][j] + ",");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
