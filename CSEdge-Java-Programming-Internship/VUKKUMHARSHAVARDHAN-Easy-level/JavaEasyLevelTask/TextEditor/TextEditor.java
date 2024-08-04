import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends Frame implements ActionListener {
    TextArea textArea;
    MenuBar menuBar;
    Menu fileMenu;
    MenuItem openItem, saveItem, closeItem;

    public TextEditor() {
        // Create the text area
        textArea = new TextArea();
        add(textArea);

        // Create the menu bar
        menuBar = new MenuBar();

        // Create the File menu
        fileMenu = new Menu("File");
        openItem = new MenuItem("Open");
        saveItem = new MenuItem("Save");
        closeItem = new MenuItem("Close");

        // Add action listeners
        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        closeItem.addActionListener(this);

        // Add menu items to the File menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);

        // Add File menu to the menu bar
        menuBar.add(fileMenu);

        // Set the menu bar
        setMenuBar(menuBar);

        // Set the frame properties
        setTitle("Text Editor");
        setSize(800, 600);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Open":
                openFile();
                break;
            case "Save":
                saveFile();
                break;
            case "Close":
                System.exit(0);
                break;
        }
    }

    private void openFile() {
        FileDialog fileDialog = new FileDialog(this, "Open File", FileDialog.LOAD);
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String fileName = fileDialog.getFile();

        if (directory != null && fileName != null) {
            File file = new File(directory, fileName);
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveFile() {
        FileDialog fileDialog = new FileDialog(this, "Save File", FileDialog.SAVE);
        fileDialog.setVisible(true);

        String directory = fileDialog.getDirectory();
        String fileName = fileDialog.getFile();

        if (directory != null && fileName != null) {
            File file = new File(directory, fileName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new TextEditor();
    }
}
