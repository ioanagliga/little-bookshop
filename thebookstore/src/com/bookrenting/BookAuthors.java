package com.bookrenting;

import java.util.*;

public class BookAuthors {

    public Map<String, List<Book>> getAuthorsAndBooks() {

        List<String> authors = new ArrayList<>();
        Map<String, List<Book>> books = new HashMap<>();

        authors.add("Frank Herbert");
        authors.add("Andrzej Sapkowski");
        authors.add("Isaac Asimov");
        authors.add("Brian Sanderson");
        authors.add("Margaret Atwood");
        authors.add("Ray Bradbury");
        authors.add("Mary Shelley");
        authors.add("George Orwell");

        Book b1 = new Book("Dune",12);
        Book b2 = new Book("Children of Dune" ,7);
        Book b3 = new Book( "Heretics of Dune",10);
        List<Book> volume0=new ArrayList<>();
        volume0.add(b1);
        volume0.add(b2);
        volume0.add(b3);
        books.put(authors.get(0), volume0);

        Book b4 = new Book("The Last Wish",13);
        Book b5 = new Book("Sword of Destiny" ,14);
        Book b6 = new Book( "Blood of Elves",15);
        Book b7 = new Book( "Time of Contempt",12);
        Book b8 = new Book( "Baptism of Fire",10);
        Book b9 = new Book( "The Tower of the Swallow",8);
        Book b10 = new Book( "The Lady of the Lake",7);
        Book b11 = new Book( "Season of Storms",9);
        List<Book> volume1=new ArrayList<>();
        volume1.add(b4);
        volume1.add(b5);
        volume1.add(b6);
        volume1.add(b7);
        volume1.add(b8);
        volume1.add(b9);
        volume1.add(b10);
        volume1.add(b11);
        books.put(authors.get(1), volume1);


        books.put(authors.get(2), Arrays.asList(new Book("Foundation Series",3)));
        books.put(authors.get(3), Arrays.asList(new Book("The Stormlight Archive Series",3)));
        books.put(authors.get(4), Arrays.asList(new Book("The Handmaid's Tale",6)));
        books.put(authors.get(5), Arrays.asList(new Book("Fahrenheit 451",9)));
        books.put(authors.get(6), Arrays.asList(new Book("Frankenstein",7)));
        books.put(authors.get(7), Arrays.asList(new Book("1984",4)));



       return  books;
    }
    public void findAndDisplayBooks( Map<String, List<Book>> books ){

        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter Author name:");
        String writerName = userInput.nextLine();
        System.out.println("author: " + writerName);


        if (books.containsKey(writerName)) {
            System.out.println("Writer found. ");
            System.out.println("Books by author: " + books.get(writerName) );
        } else {
            System.out.println("Writer Not found.");
        }
    }
}
