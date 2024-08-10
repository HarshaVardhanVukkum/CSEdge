import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("unused")
public class LibraryResourceAdministrator extends Frame implements ActionListener {
    TextField bookNameField, authorNameField;
    TextArea displayArea;
    Button addButton, displayButton;
    ArrayList<Book> books;

    public LibraryResourceAdministrator() {
        books = new ArrayList<>();

        setLayout(new FlowLayout());

        Label bookNameLabel = new Label("Book Name:");
        add(bookNameLabel);

        bookNameField = new TextField(20);
        add(bookNameField);

        Label authorNameLabel = new Label("Author Name:");
        add(authorNameLabel);

        authorNameField = new TextField(20);
        add(authorNameField);

        addButton = new Button("Add Book");
        addButton.addActionListener(this);
        add(addButton);

        displayButton = new Button("Display Books");
        displayButton.addActionListener(this);
        add(displayButton);

        displayArea = new TextArea(10, 40);
        add(displayArea);

        setTitle("Library Resource Administrator");
        setSize(500, 300);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            String bookName = bookNameField.getText();
            String authorName = authorNameField.getText();
            books.add(new Book(bookName, authorName));
            bookNameField.setText("");
            authorNameField.setText("");
        } else if (ae.getSource() == displayButton) {
            displayArea.setText("");
            for (Book book : books) {
                displayArea.append("Book: " + book.getName() + ", Author: " + book.getAuthor() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        new LibraryResourceAdministrator();
    }

    class Book {
        private String name;
        private String author;

        public Book(String name, String author) {
            this.name = name;
            this.author = author;
        }

        public String getName() {
            return name;
        }

        public String getAuthor() {
            return author;
        }
    }
}
