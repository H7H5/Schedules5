package testgroup.service.parseSchedules;


import java.util.ArrayList;

public class SelectedGroup {

    public ArrayList<GroupCellDTO> selectGroup(ArrayList<GroupCellDTO> dirtyGroups){
        for (int i = 0; i <dirtyGroups.size() ; i++) {

            String temp = dirtyGroups.get(i).group.replaceAll("\\s+","");
            dirtyGroups.get(i).group = temp;
        }

        return deleteEmptyString(dirtyGroups);

    }
    private ArrayList<GroupCellDTO> deleteEmptyString(ArrayList<GroupCellDTO> dirtyGroups){
        for (int i = 0; i <dirtyGroups.size() ; i++) {
            if(dirtyGroups.get(i).group.length()<1){
                dirtyGroups.remove(i);
                i--;
            }
        }
        return deleteEndPoint(dirtyGroups);
    }

    private ArrayList<GroupCellDTO> deleteEndPoint(ArrayList<GroupCellDTO> dirtyGroups){
        for (int i = 0; i <dirtyGroups.size() ; i++) {
            char ch = dirtyGroups.get(i).group.charAt(dirtyGroups.get(i).group.length() - 1);
            //System.out.println(ch);
            if(ch == '.'){
                dirtyGroups.remove(i);
            }
        }
        return getCleanGroup(dirtyGroups);
    }
    private ArrayList<GroupCellDTO> getCleanGroup(ArrayList<GroupCellDTO> dirtyGroups){
        ArrayList<GroupCellDTO> cleanGroups = new ArrayList<>();
        String delimiter = "\\.";
        for (int i = 0; i <dirtyGroups.size() ; i++) {
            GroupCellDTO groupCellDTO = dirtyGroups.get(i);
            String[] subData = groupCellDTO.group.split(delimiter);
            groupCellDTO.group = subData[subData.length-1];
            cleanGroups.add(groupCellDTO);
        }
        return cleanGroups;
    }
}
