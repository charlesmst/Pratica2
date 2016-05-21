package br.com.empresa.rh.model.request;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Set;
import javax.persistence.Query;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author charles
 */
public class TableRequest {

    private String filter;
    private int limit;
    private String order;
    private int page = -1;
    //1 ASC, -1 DESC
    private int direction;

    public static TableRequest build(UriInfo url) {
        TableRequest request = new TableRequest();
        MultivaluedMap<String, String> queryString = url.getQueryParameters();
        if (queryString.containsKey("filter")) {
            request.setFilter(queryString.getFirst("filter"));
        }
        if (queryString.containsKey("limit") && queryString.getFirst("limit").matches("^\\d+$")) {
            request.setLimit(Integer.parseInt(queryString.getFirst("limit")));
        }
        if (queryString.containsKey("page") && queryString.getFirst("page").matches("^\\d+$")) {
            request.setPage(Integer.parseInt(queryString.getFirst("page")) - 1);
        }
        if (queryString.containsKey("order") && queryString.getFirst("order").matches("^\\w+$")) {
            //Verifica se é desc
            request.setOrder(queryString.getFirst("order"));
        }
        if (queryString.containsKey("order") && queryString.getFirst("order").startsWith("-")) {
            request.setDirection(-1);
            request.setOrder(queryString.getFirst("order").substring(1));
        } else {
            request.setDirection(1);
        }
        return request;
    }

    private HashMap<String, Object> parameters = new HashMap<String, Object>();

    public String applyFilter(String... columns) {
        if (getFilter() != null && !getFilter().trim().equals("")) {
            String hql = " where ";
            if (getFilter().matches("^\\d+$")) {
                parameters.put("filter", getFilter() + "%");
            } else {
                parameters.put("filter", "%" + getFilter().trim() + "%");
            }

            for (String column : columns) {
                hql += "lower(str(" + column + "))" + " like lower(:filter) or ";
            }
            hql = hql.substring(0, hql.length() - 3);
            return hql;
        }
        return "";
    }

    public void applyParameters(Query q) {
        for (String parameter : parameters.keySet()) {
            q.setParameter(parameter, parameters.get(parameter));
        }
    }

    public String applyOrder(String... columns) {
        String hql = "";

        if (getOrder() != null && !getOrder().trim().equals("")) {
            for (String column : columns) {
                if (column.toLowerCase().equals(getOrder().toLowerCase())) {
                    hql += " order by " + column + " " + (getDirection() > 0 ? "ASC" : "DESC");
                    break;
                }
            }
        }
        return hql;
    }

    public void applyPagination(Query q) {
        if (getLimit() > 0 && getPage() >= 0) {
            q.setFirstResult(getPage() * getLimit());
            q.setMaxResults(getLimit());
        }
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        if (order.startsWith("-")) {
            this.direction = -1;
            this.order = order.substring(1);
        } else {
            this.order = order;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
