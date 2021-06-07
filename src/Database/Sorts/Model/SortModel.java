package Database.Sorts.Model;

public class SortModel {
    int idSort;
    String name;
    String sort;

    public SortModel() {
    }

    public int getIdSort() {
        return idSort;
    }

    public void setIdSort(int idSort) {
        this.idSort = idSort;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
