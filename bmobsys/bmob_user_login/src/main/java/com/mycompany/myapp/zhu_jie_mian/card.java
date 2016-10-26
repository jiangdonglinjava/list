package com.mycompany.myapp.zhu_jie_mian;



/**
 * Created by cys on 15/6/24.
 *
 */
public class card {

    private String id ;
    private String url ;
    private String title ;
	private Boolean SelectIs;
	private int index;

	public void setIndex(int index)
	{
		this.index = index;
	}

	public int getIndex()
	{
		return index;
	}

	public void setSelectIs(Boolean selectIs)
	{
		this.SelectIs = selectIs;
	}

	public Boolean getSelectIs()
	{
		return SelectIs;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public card() {
    }
}
