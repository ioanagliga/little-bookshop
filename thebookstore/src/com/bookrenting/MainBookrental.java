package com.bookrenting;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainBookrental {

    public static void main(String[] args) throws IOException {
        BookAuthors booksAndAuthors = new BookAuthors();
        List<Book> books =booksAndAuthors.initialiseData("D:\\Progrumming\\little-bookshop\\thebookstore\\database.txt");
        Map<String, List<Book>> bookAndAuthorsMapping = booksAndAuthors.groupData(books);
        Scanner userInput =new Scanner(System.in);
        System.out.println("Enter author name: ");
        String author = userInput.nextLine();
        booksAndAuthors.searchAndDisplay(author,bookAndAuthorsMapping);

    }
}