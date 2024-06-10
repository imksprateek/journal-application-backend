package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//The above @SpringBootApplication is equivalent to using 3 annotations: @EnableAutoConfiguration, @ComponentScan and @Configuration
@EnableTransactionManagement
//@EnableTransactionManagement annotation makes Spring create a container for all methods annotated with @Transactional and treat the entire method body as a single operation
//    Use @Transactional annotation to carry out the method steps as a single unit (single operation). If any step fails inside the method, the changes are reverted. This is used for doing any save/delete/update operation on the database. You also need to add @EnableTransactionManagement annotation to the main Application class. When there are 2 concurrent requests, it creates 2 different containers in Transaction context and executes separately for each request
//    PlatformTransactionManager.class annotation and it's implementation MongoTransactionManager.class carry out transactions and rollback when any of the steps fail
public class JournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JournalApplication.class, args);
    }

    //We need to create a bean for handling Database Transactions. We return an implementation of PlatformTransactionManager to handle transactions
//    This can also be done by creating a TransactionConfig class under config package and adding the below bean there (Annotate TransactionConfig class with @Configuration)
    @Bean
    public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
//        When we specify DB instance details in application.properties file, an implementation of MongoDatabaseFactory is created, which is passed by Spring as dbFactory to this method (The implementation class is SimpleMongoClientDatabaseFactory.class)
//        MongoDatabaseFactory is used to connect to MongoDB
        return new MongoTransactionManager(dbFactory);
    }

}