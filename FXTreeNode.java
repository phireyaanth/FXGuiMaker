/*
PHIREYAANTH POOBALARAJ
114873057
REC 1
 */
public class FXTreeNode {
    private String text;
    private CompnentType type;
    private FXTreeNode parent;
    private FXTreeNode[] children;
    private int maxChildren = 10;
    private int spaces;

    public FXTreeNode(String type, int spaces){
        this.spaces = spaces;
        this.parent = null;
        String type_CPAS = type.toUpperCase();
        switch(type_CPAS){
            case "BUTTON":
                this.type = CompnentType.Button;
                break;
            case "LABEL":
                this.type = CompnentType.Label;
                break;
            case "TEXTAREA":
                this.type = CompnentType.TextArea;
                break;
            case "HBOX":
                this.type = CompnentType.Hbox;
                break;
            case "VBOX":
                this.type = CompnentType.VBox;
                break;
            case "ANCHORPANE":
                this.type = CompnentType.AnchorPane;
                break;
        }
        this.children = new FXTreeNode[maxChildren];
    }

    public void setSpaces(int spaces){
        this.spaces = spaces;
    }

    public int getSpaces(){
        return this.spaces;
    }


    public String getText(){
        return this.text;
    }

    public CompnentType getType(){
        return this.type;
    }

    public FXTreeNode getParent(){
        return this.parent;
    }

    public FXTreeNode[] getChildren() {
        return this.children;
    }

    public FXTreeNode getChildrenAtPos(int pos){
        return this.children[pos];
    }

    public int getMaxChildren(){
        return this.maxChildren;
    }

    public void setText(String text){
        this.text = text;
    }

    public void setType(CompnentType type){
        this.type = type;
    }

    public void setParent(FXTreeNode parent){
        this.parent = parent;
    }

    public void setChildren(FXTreeNode[] children){
        this.children = children;
    }

    public void setChildrenAtPos(FXTreeNode children, int pos){
        this.children[pos] = children;
    }

    public void setMaxChildren(int maxChildren){
        this.maxChildren = maxChildren;
    }

}

