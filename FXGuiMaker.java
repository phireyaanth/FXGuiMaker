/*
PHIREYAANTH POOBALARAJ
114873057
REC 1
 */

import java.io.FileNotFoundException;
import java.util.Scanner;

public class FXGuiMaker {
    private static FXComponentTree tree;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to counterfeit SceneBuilder");
        print();
        String unedited_input = sc.nextLine();
        String input = unedited_input.toUpperCase();

        while(!input.equals("Q")){
            switch(input){
                case "L":
                    System.out.println("Please enter filename: ");
                    String filepath = sc.nextLine();

                    tree = FXComponentTree.readFromFile2(filepath);

                    if(tree != null){
                        System.out.println(filepath + " loaded");
                        tree.cursorToRoot();
                    }
                    /*
                    tree.cursorToChild(0);
                    tree.cursorToChild(0);
                    System.out.println(tree.getCursor().getType().toString());

                     */

                    print();
                    break;
                case "P":
                    FXTreeNode root_node = tree.getRoot();
                    print_method(root_node, tree);
                    print();
                    break;
                case "C":
                    System.out.println("Please enter number of child (starting with 1): ");
                    int index = sc.nextInt() - 1;
                    tree.cursorToChild(index);
                    if(tree.getCursor().getType() == CompnentType.AnchorPane ||tree.getCursor().getType() == CompnentType.VBox || tree.getCursor().getType() == CompnentType.Hbox) {
                        System.out.println("Cursor Moved to " + tree.getCursor().getType().toString());
                    }
                    else{
                        System.out.println("Cursor Moved to " + tree.getCursor().getType().toString() + ": "+ tree.getCursor().getText());
                    }
                    print();
                    break;
                case "A":
                    System.out.println("Select component type (H - HBox," +
                            " V - VBox, T - TextArea, B - Button, L - Label): ");
                    String choice = sc.nextLine().toUpperCase();
                    System.out.println("Please enter text: ");
                    String text = sc.nextLine();
                    System.out.println("Please enter an index: ");
                    int index2 = sc.nextInt();
                    int newSpaces = tree.getCursor().getSpaces() + 1;
                    FXTreeNode newNode = null;
                    switch(choice){
                        case "H":
                            newNode = new FXTreeNode("Hbox", newSpaces);
                            break;
                        case "V":
                            newNode = new FXTreeNode("Vbox", newSpaces);
                            break;
                        case "T":
                            newNode = new FXTreeNode("TextArea", newSpaces);
                            break;
                        case "B":
                            newNode = new FXTreeNode("Button", newSpaces);
                            break;
                        case "L":
                            newNode = new FXTreeNode("Label", newSpaces);
                            break;
                    }
                    try {
                        tree.addChildren(index2 - 1,newNode);
                        newNode.setText(text);
                        tree.setCursor(newNode);
                    } catch (ChildNodeListException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Added");
                    print();
                    break;
                case "U":
                    tree.cursorToParent();
                    System.out.println("Cursor Moved to " + tree.getCursor().getType().toString());
                    print();
                    break;
                case "E":
                    System.out.println("Please enter new text: ");
                    String newText = sc.nextLine();
                    if(tree.getCursor().getType() == CompnentType.AnchorPane ||tree.getCursor().getType() == CompnentType.VBox || tree.getCursor().getType() == CompnentType.Hbox) {
                        System.out.println("Cannot edit text");

                    }
                    else{
                        if(tree.getCursor() == null){
                            System.out.println("Cannot edit text");
                        }
                        else{
                            tree.getCursor().setText(newText);
                            System.out.println("Text Edited.");
                        }
                    }

                    print();
                    break;
                case "D":
                    System.out.println("Please enter number of child (starting with 1): ");
                    int index3 = sc.nextInt();
                    if(tree.getCursor().getChildrenAtPos(index3-1) != null) {
                        String name = tree.getCursor().getChildrenAtPos(index3 - 1).getType().toString();
                        tree.deleteChild(index3 - 1);
                        System.out.println(name + " removed.");
                    }
                    else{
                        System.out.println("IndexOutOfBounds");
                    }
                    print();
                    break;
                case "R":
                    tree.cursorToRoot();
                    System.out.println("Cursor is at root.");
                    print();
                    break;
                case "S":
                    System.out.println("Please enter a filename: ");
                    String filename = sc.nextLine();
                    FXComponentTree.writeToFile2(tree, filename);
                    System.out.println(filename + " saved to computer");
                    print();
                    break;
                case "Q":
                    System.out.print("Make like a tree and leave!");
                    break;
            }
            input = sc.nextLine().toUpperCase();
        }

    }

    public static void print(){

        System.out.println("Menu: \n" +
                "L) Load from file \n" +
                "P) Print tree \n" +
                "C) Move cursor to a child node \n" +
                "R) Move cursor to root \n" +
                "A) Add a child \n" +
                "U) Cursor up (to parent) \n" +
                "E) Edit text of cursor \n" +
                "D) Delete child \n" +
                "S) Save to file \n" +
                "X) Export FXML \n" +
                "Q) Quit");
        System.out.println("Please select an option: ");



    }

    public static void print_method(FXTreeNode node, FXComponentTree newTree){
        if(node != null){

            if(node == newTree.getCursor()){
                for(int i = 0; i < node.getSpaces(); i++){
                    System.out.print("  ");
                }
                if(node.getType() == CompnentType.AnchorPane ||node.getType() == CompnentType.VBox || node.getType() == CompnentType.Hbox) {
                    System.out.println("==>" + node.getType().toString());
                }
                else{
                    System.out.println("==>" + node.getType().toString() + ": " + node.getText());
                }
            }
            else{
                for(int i = 0; i < node.getSpaces(); i++){
                    System.out.print("  ");
                }
                if(node.getType() == CompnentType.AnchorPane ||node.getType() == CompnentType.VBox || node.getType() == CompnentType.Hbox) {
                    System.out.println("+--" + node.getType().toString());
                }
                else{
                    System.out.println("+--" + node.getType().toString() + ": " + node.getText());
                }
            }
            for (int i = 0; i < node.getChildren().length; i++) {
                print_method(node.getChildrenAtPos(i), newTree);
            }
        }
    }
}
