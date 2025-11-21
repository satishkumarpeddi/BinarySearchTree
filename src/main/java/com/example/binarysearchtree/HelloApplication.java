package com.example.binarysearchtree;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.Group;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import java.io.IOException;

public class HelloApplication extends Application {
    BSTree tree = new BSTree();
    Group canvas = new Group();
   static Label messageLabel = new Label("Contention : ");
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Label headingLabel = new Label("Binary Search Tree Visualization ---> Developed By Satish Kumar Peddi 🚀🚀");
        TextField valueField = new TextField();
        valueField.setPromptText("Enter value ");
        Button insertBtn = new Button("Insert");
        Button deleteBtn = new Button("Delete");
        Button searchBtn = new Button("Search");
        insertBtn.setOnAction(e->{
           try{
             int value = Integer.parseInt(valueField.getText());
             tree.insert(value);
             drawTree();
             valueField.clear();
           }catch(Exception ignored){
           }
        });
        deleteBtn.setOnAction(e->{
            try{
                int value = Integer.parseInt(valueField.getText());
                tree.delete(value);
                drawTree();
                valueField.clear();
            }catch(Exception ignored){

            }
        });
        searchBtn.setOnAction(e->{
            try{
                int value = Integer.parseInt(valueField.getText());
                tree.search(value);
                valueField.clear();
            }catch(Exception ignored){

            }
        });
        messageLabel.setStyle("-fx-background-color: red;-fx-text-fill:white;-fx-font-weight: bold");
        headingLabel.setStyle("-fx-text-fill: brown;-fx-font-weight: bold;-fx-font-size:20");
        headingLabel.setPadding(new Insets(10));
        messageLabel.setPadding(new Insets(10));
        HBox hBox = new HBox(10,valueField,insertBtn,deleteBtn,searchBtn);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));
        BorderPane  root = new BorderPane();
        root.setTop(headingLabel);
        root.setLeft(messageLabel);
        root.setCenter(canvas);
        root.setBottom(hBox);
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
        void drawTree(){
            canvas.getChildren().clear();
            drawNode(tree.root,450,50,100);
        }
        void drawNode(Node node,double x,double y,double gap){
            if(node==null) return;
            node.x=x;
            node.y=y;
            if(node.left!=null){
                Line line = new Line(x,y,x-gap,y+70);
                canvas.getChildren().add(line);
                drawNode(node.left,x-gap,y+70,gap/2);

            }
            if(node.right!=null){
                Line line = new Line(x,y,x+gap,y+70);
                canvas.getChildren().add(line);
                drawNode(node.right,x+gap,y+70,gap/2);
            }
            Circle circle = new Circle(x,y,20,Color.ANTIQUEWHITE);
            circle.setStroke(Color.DARKRED);
            circle.setStrokeWidth(2);
            Text text = new Text(x-6,y+4,""+node.data);
            text.setFill(Color.DARKCYAN);
            text.setStyle("-fx-font-weight: bold;-fx-font-size:10");
            canvas.getChildren().addAll(circle,text);
        }
        static class Node{
            int data;
            double x,y;
            Node left;
            Node right;
            Node(int data){
                this.data=data;
            }
        }
        static class BSTree{
            Node root;
            Node insert(Node root,int data){
                if(root==null)
                    return new Node(data);
                if(root.data>data)
                    root.left= insert(root.left,data);
                else if(root.data<data)
                    root.right = insert(root.right,data);
                else
                    messageLabel.setText("Contention : The element already exists.");
                return root;
            }
            Node minValueNode(Node root){
                Node currentNode = root;
                while(currentNode.left!=null)
                        currentNode=currentNode.left;
                return currentNode;
            }

            Node delete(Node root,int data){
                if(root==null)
                    return root;

                if(root.data>data)
                    root.left=delete(root.left,data);
                else if(root.data<data)
                    root.right=delete(root.right,data);
                else if(root.data==data){
                    if(root.left==null)
                        return root.right;
                    if(root.right==null)
                        return root.left;
                    Node temp = minValueNode(root.right);
                    root.data=temp.data;
                    root.right = delete(root.right,temp.data);
                }else{
                      messageLabel.setText("Contention : There is no such element in Binary Search Tree") ;
                }
                return root;
            }
            Node search(Node root,int data){
                if(root==null) {
                    messageLabel.setText("Contention : The element not  found in Binary Search Tree");
                    return root;
                }
                if(root.data==data) {
                    messageLabel.setText("Contention : The element is present in Binary Search Tree");
                    return root;
                }
                else if(root.data>data)
                    return search(root.left,data);
                else if(root.data<data)
                    return search(root.right,data);

                return root;
            }
            void insert(int data){ root = insert(root,data);}
            void delete(int data){root = delete(root,data);}
            void search(int data){root = search(root,data); }


        }


}
