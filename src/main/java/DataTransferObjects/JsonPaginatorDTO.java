package DataTransferObjects;

public class JsonPaginatorDTO {
    private int pageSize;
    private int pageIndex;

    public JsonPaginatorDTO(int pageSize, int pageIndex) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    public JsonPaginatorDTO(){}

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
