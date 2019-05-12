package ru.ryu.entity;

import javax.persistence.*;

@Entity
@Table(name = "WORDS")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String parent;
    @Column(name = "FILE_NAME")
    private String fileName;
    private String word;
    private int count;

    Word() {

    }

    public Word(String parent, String fileName, String word) {
        this.parent = parent;
        this.fileName = fileName;
        this.word = word;
        this.count = 1;
    }

    public Word(int id, String parent, String fileName, String word, int count) {
        this.id = id;
        this.parent = parent;
        this.fileName = fileName;
        this.word = word;
        this.count = count;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getParent() {
        return parent;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Word{" +
                "parent = '" + parent + "'" +
                ", fileName = '" + fileName + "'" +
                ", word = '" + word + "'" +
                ", count = " + count +
                '}';
    }
}
