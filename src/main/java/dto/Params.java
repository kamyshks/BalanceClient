package dto;

import java.util.List;

public class Params {
    private final Integer rCount;
    private final Integer wCount;
    private final List<Integer> idList;

    public Params(final Integer rCount, final Integer wCount, final List<Integer> idList){
        this.rCount = rCount;
        this.wCount = wCount;
        this.idList = idList;
    }

    public Integer getRCount(){ return rCount; }
    public Integer getWCount(){ return wCount; }
    public List<Integer> getIdList(){ return idList; }
}
