package dasturlash.uz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {
//        insertStudents();
//        simpleForm();
//        distinctExample();
        useAll();
    }

    private static void simpleForm() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        String sql = "select count(*) From StudentEntity";
        Query<Long> query = session.createQuery(sql);
        Long count = query.uniqueResult();
        System.out.println("Total student count: " + count);

        factory.close();
        session.close();
    }

    private static void distinctExample() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        String sql = "select count(distinct name) From StudentEntity";
        Query<Long> query = session.createQuery(sql);
        Long count = query.uniqueResult();
        System.out.println("Total student name count: " + count);

        factory.close();
        session.close();
    }

    private static void useAll() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();

        String sql = "select count(*), sum(age), max(age), min(age),  avg(age) From StudentEntity";
        Query<Object[]> query = session.createQuery(sql);
        Object[] objs = query.uniqueResult();
        Long count = (Long) objs[0];
        Long sum = (Long) objs[1];
        Integer max = (Integer) objs[2];
        Integer min = (Integer) objs[3];
        Double avg = (Double) objs[4];

        System.out.println(count);
        System.out.println(sum);
        System.out.println(max);
        System.out.println(min);
        System.out.println(avg);

        factory.close();
        session.close();
    }

    public static void insertStudents() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        StudentEntity student1 = new StudentEntity();
        student1.setName("Ali");
        student1.setSurname("Aliyev");
        student1.setAge(22);
        student1.setGroupName("alfa");
        session.save(student1);

        StudentEntity student2 = new StudentEntity();
        student2.setName("Vali");
        student2.setSurname("Valiyev");
        student2.setAge(24);
        student2.setGroupName("betta");
        session.save(student2);

        StudentEntity student3 = new StudentEntity();
        student3.setName("Toshmat");
        student3.setSurname("Toshmatov");
        student3.setAge(19);
        student3.setGroupName("delta");
        session.save(student3);

        StudentEntity student4 = new StudentEntity();
        student4.setName("Eshmat");
        student4.setSurname("Eshmatov");
        student4.setAge(28);
        student4.setGroupName("Mazgi");
        session.save(student4);

        t.commit();

        factory.close();
        session.close();
    }
}