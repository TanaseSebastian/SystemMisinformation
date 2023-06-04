package Model;

public class Ruolo {
 private int id;
 private String nome;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public Ruolo(int id, String nome) {
	super();
	this.id = id;
	this.nome = nome;
}
public Ruolo() {
	super();
}
@Override
public String toString() {
	return "Ruolo [id=" + id + ", nome=" + nome + "]";
}

}
