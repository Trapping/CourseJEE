package dbSevice.dao;

import dbSevice.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String name) throws HibernateException{
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        UsersDataSet dataSet = ((UsersDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult());
        if (dataSet != null){
            return dataSet.getId();
        } else return 0;
    }

    public long insertUser(String name) throws HibernateException{
        return (long) session.save(new UsersDataSet(name));
    }
}
