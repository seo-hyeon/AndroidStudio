package ddwucom.mobile.test08.adapterclicktest;

import java.util.ArrayList;

public class SubjectManager {
    private ArrayList<String> subjectList;

    public SubjectManager() {
        subjectList = new ArrayList();
        subjectList.add("크리스피치킨");
        subjectList.add("양념치킨");
        subjectList.add("스노우치킨");
        subjectList.add("뿌링클");
        subjectList.add("고추 바사삭 치킨");
    }

    public ArrayList<String> getSubjectList() {
        return subjectList;
    }

//    추가
    public void addData(String newSubject) {
        subjectList.add(newSubject);
    }

//    삭제
    public void removeData(int idx) {
        subjectList.remove(idx);
    }

    public void updateData(int pos, String updateSubject){ subjectList.set(pos, updateSubject); }

    public String getItem(int pos){ return subjectList.get(pos); }

}
