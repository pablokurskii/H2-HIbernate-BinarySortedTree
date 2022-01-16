package org.albistudio.mainops;

import org.albistudio.core.HibernateUtils;
import org.albistudio.mainops.model.BinarySortedTreeH2;
import org.albistudio.mainops.model.NodeH2;
import org.h2.tools.Console;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";

    public static void main(String[] args) throws SQLException {
        SessionFactory sf = HibernateUtils.buildSessionFactory(HIBERNATE_CFG_FILE, NodeH2.class);
        System.out.println("\n\n-------------------------------------------\n\n");
        System.out.println("Inserting nodes into DB: ");
        try (var session = sf.openSession()) {
            var nodes = List.of(1, 3, 10, 1, 6, 14, 4, 7, 13);

            session.getTransaction().begin();

            BinarySortedTreeH2 bt = BinarySortedTreeH2.getInstance();
            bt.setSession(session);

            for (Integer node : nodes) {
                bt.add(node);
            }

            bt.traverseLevelOrder();

            session.getTransaction().commit();

            System.out.println("\n\n-------------------------------------------\n\n");
            System.out.println("Retrieving all Nodes:");
            session.getTransaction().begin();
            TypedQuery<NodeH2> query = session.createQuery("select n from NodeH2 n", NodeH2.class);
            List<NodeH2> resultList = query.getResultList();

            System.out.println("\n\nResult:");
            System.out.println(resultList);
            session.getTransaction().commit();
        }
        Console.main();
    }

}
