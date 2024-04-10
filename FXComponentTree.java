/*
PHIREYAANTH POOBALARAJ
114873057
REC 1
 */
import java.io.*;
import java.util.Arrays;

public class FXComponentTree {
    private FXTreeNode root;
    private FXTreeNode cursor;

    public FXComponentTree() {
        root = null;
        cursor = root;
    }

    public void setRoot(FXTreeNode root){
        this.root = root;

    }

    public FXTreeNode getRoot(){
        return this.root;
    }

    public void setCursor(FXTreeNode cursor){
        this.cursor = cursor;
    }

    public FXTreeNode getCursor(){
        return this.cursor;
    }

    public void cursorToRoot () {
        this.cursor = this.root;
    }

    public void deleteChild(int index){
        FXTreeNode[] newlist = new FXTreeNode[this.cursor.getMaxChildren()];
        FXTreeNode[] currlst = this.cursor.getChildren();
        for(int i = 0; i < currlst.length; i++){
            if(i != index){
                newlist[i] = currlst[i];
            }
        }
        this.cursor.setChildren(newlist);
    }

    public void addRoot(FXTreeNode node){
        this.root = node;
    }

    public void addChildren(int index, FXTreeNode node) throws ChildNodeListException{
        if(this.cursor.getChildrenAtPos(index) == null){
            this.cursor.setChildrenAtPos(node, index);
        }
        else{
            for (int i = this.cursor.getChildren().length - 1; i > index; i--) {
                this.cursor.getChildren()[i] = this.cursor.getChildren()[i - 1];
            }

            this.cursor.getChildren()[index] = node;
        }

/*
        for(int i = 0; i < this.cursor.getChildren().length; i++){
            if(this.cursor.getChildren()[i] == null){
                for(int j = i + 1; j < this.cursor.getChildren().length;j++){
                    if(this.cursor.getChildren()[j] == null){
                        throw new ChildNodeListException("Holes in ChildList");
                    }
                }
            }
        }
*/

    }

    public void cursorToChild(int index){
        this.cursor = this.cursor.getChildrenAtPos(index);
    }

    public void cursorToParent() {
        this.cursor = cursor.getParent();
    }





    public static void writeToFile2(FXComponentTree tree, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            preorderTraversal2(tree.getRoot(), "0", writer);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void preorderTraversal2(FXTreeNode node, String location, FileWriter writer) throws IOException {
        if (node != null) {
            // Get the type of the node


            String typeString = node.getType().toString();

            // Write the node's information to the file
            writer.write(location  + " " + node.getType().toString()+ " "+ node.getText() + "\n");
            //System.out.println(node.getType());
            // Traverse the children of the node recursively
            for (int i = 0; i < node.getChildren().length; i++) {
                preorderTraversal2(node.getChildrenAtPos(i), location + "-" + i, writer);
            }
        }
    }


    public static FXComponentTree readFromFile2(String filename) {
        String path = filename;
        FXComponentTree newTree = new FXComponentTree();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line based on spaces
                String[] parts = line.split(" ");
                /*
                for(int i = 0; i < parts.length; i++) {
                    System.out.print(parts[i] + " ");
                    System.out.println("");
                }
                */

                String enum_ref = parts[1].toUpperCase();
                //System.out.println(enum_ref);
                // Extract hierarchy numbers and node type
                String[] treeNumbers = parts[0].split("-");
                // Extract node text
                String text = "";
                for (int i = 2; i < parts.length; i++) {
                    text += parts[i] + " ";
                }


                // Create a new node based on its type
                FXTreeNode newNode = new FXTreeNode(parts[1], parts[0].length() / 2);
                newNode.setText(text);
                //System.out.println(newNode.getText());
                // If it's the root node, set it as the root of the tree
                if (treeNumbers.length == 1) {
                    newTree.setRoot(newNode);
                } else {
                    // Traverse the tree to the correct parent node
                    FXTreeNode parent = newTree.getRoot();
                    for (int i = 1; i < treeNumbers.length - 1; i++) {
                        int index = Integer.parseInt(treeNumbers[i]);
                        if (parent.getChildrenAtPos(index) == null) {
                            FXTreeNode intermediateNode = new FXTreeNode(enum_ref, parts[0].length() / 2);
                            parent.setChildrenAtPos(intermediateNode, index);
                            intermediateNode.setParent(parent);
                        }
                        parent = parent.getChildrenAtPos(index);
                    }

                    // Set the new node as a child of its parent
                    int lastIndex = Integer.parseInt(treeNumbers[treeNumbers.length - 1]);
                    parent.setChildrenAtPos(newNode, lastIndex);
                    newNode.setParent(parent);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(filename + " not found");
            return null;
        } catch (IOException e) {
            e.printStackTrace();

        }

        return newTree;
    }










}
