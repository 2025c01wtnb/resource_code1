// Book.java - 書籍クラス
public class Book {
    private int id;
    private String title;
    private String author;
    private int publicationYear;

    public Book(int id, String title, String author, int publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", タイトル: " + title + ", 著者: " + author + ", 出版年: " + publicationYear;
    }
}

// BookManager.java - 書籍管理クラス
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private List<Book> books;
    private int nextId;

    public BookManager() {
        this.books = new ArrayList<>();
        this.nextId = 1;
    }

    // 書籍の登録
    public Book registerBook(String title, String author, int publicationYear) {
        Book book = new Book(nextId, title, author, publicationYear);
        books.add(book);
        nextId++;
        return book;
    }

    // 全書籍のリストを取得
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}

// BookManagementSystem.java - メインクラス
import java.util.List;
import java.util.Scanner;

public class BookManagementSystem {
    private static BookManager bookManager = new BookManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("書籍管理システムへようこそ！");

        while (running) {
            System.out.println("\n==== メニュー ====");
            System.out.println("1. 書籍を登録する");
            System.out.println("2. 全書籍を表示する");
            System.out.println("0. 終了");
            System.out.print("選択してください: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 改行を消費

            switch (choice) {
                case 0:
                    running = false;
                    System.out.println("システムを終了します。");
                    break;
                case 1:
                    registerBook();
                    break;
                case 2:
                    displayAllBooks();
                    break;
                default:
                    System.out.println("無効な選択です。もう一度試してください。");
            }
        }
        
        scanner.close();
    }

    private static void registerBook() {
        System.out.println("\n=== 書籍登録 ===");
        
        System.out.print("タイトル: ");
        String title = scanner.nextLine();
        
        System.out.print("著者: ");
        String author = scanner.nextLine();
        
        System.out.print("出版年: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine(); // 改行を消費
        
        Book book = bookManager.registerBook(title, author, publicationYear);
        System.out.println("書籍が登録されました: " + book);
    }

    private static void displayAllBooks() {
        System.out.println("\n=== 登録済み書籍一覧 ===");
        List<Book> allBooks = bookManager.getAllBooks();
        
        if (allBooks.isEmpty()) {
            System.out.println("登録された書籍はありません。");
        } else {
            for (Book book : allBooks) {
                System.out.println(book);
            }
        }
    }
}