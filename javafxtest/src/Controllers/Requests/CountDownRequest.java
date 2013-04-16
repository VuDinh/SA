package Controllers.Requests;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Author Names: Dang Hoang Long - Vu Dinh
 * Author IDs: s3324816 - s3324817
 * Date: 4/16/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class CountDownRequest implements Serializable {
    int count;
    public CountDownRequest(){

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
