package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootsrap implements ApplicationListener<ContextRefreshedEvent>{

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootsrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository=publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
   initData();;
    }

    private void initData(){
        //Eric
        Author eric= new Author("Eric","Evans");
        Publisher pub1= new Publisher("Harper Collins","1 Harper Street");
        Book ddd= new Book("Domain Driven Design","1234",pub1);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(pub1);
        authorRepository.save(eric);

        bookRepository.save(ddd);
        //Rod
        Author rod= new Author("Rod","Johnson");
        Publisher pub2= new Publisher("Worx","1 Worx Street");
        Book noEJB= new Book("J2EE Development without EJB","23444",pub2);
        eric.getBooks().add(noEJB);
        ddd.getAuthors().add(rod);
        publisherRepository.save(pub2);
        authorRepository.save(rod);

        bookRepository.save(noEJB);
    }

}
