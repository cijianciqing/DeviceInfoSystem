package jsjzx.wlyw.networkmaintenance.entities;

public class SearchInfo {

    private Integer pageSize;
    private Integer pageNumber;
    private String searchText;
    private String sortName;
    private String sortOrder;
    private String soIP;
    private String soBieMing;
    private String soHostName;

    public String getSoIP() {
        return soIP;
    }

    public void setSoIP(String soIP) {
        this.soIP = soIP;
    }

    public String getSoBieMing() {
        return soBieMing;
    }

    public void setSoBieMing(String soBieMing) {
        this.soBieMing = soBieMing;
    }

    public String getSoHostName() {
        return soHostName;
    }

    public void setSoHostName(String soHostName) {
        this.soHostName = soHostName;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        return "SearchInfo{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", searchText='" + searchText + '\'' +
                ", sortName='" + sortName + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                ", soIP='" + soIP + '\'' +
                ", soBieMing='" + soBieMing + '\'' +
                ", soHostName='" + soHostName + '\'' +
                '}';
    }
}
