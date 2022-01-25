package testgroup.service.parseSchedules;

/**
 * Created by admin on 23.01.2022.
 */
public class GroupCellDTO {
    public int sheet;
    public int row;
    public int cell;
    public String group;

    public GroupCellDTO(int sheet, int row, int cell, String group) {
        this.sheet = sheet;
        this.row = row;
        this.cell = cell;
        this.group = group;
    }

    @Override
    public String toString() {
        return "GroupCellDTO{" +
                "sheet=" + sheet +
                ", row=" + row +
                ", cell=" + cell +
                ", group='" + group + '\'' +
                '}';
    }
}

