package app.printec.myapplication.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class JsonResponse {

    @SerializedName("fact")
    private String fact;
    @SerializedName("length")
    private int factSize;
    @SerializedName("author")
    private String author;

    @NonNull
    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public int getFactSize() {
        return factSize;
    }

    public void setFactSize(int factSize) {
        this.factSize = factSize;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "fact='" + fact + '\'' +
                ", factSize=" + factSize +
                ", author='" + author + '\'' +
                '}';
    }
}
