public class Student {
    private String id;
    private String name;
    private String email;
    private int age;
    public Student(String id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", Email=" + email + ", Age=" + age + "]";
    }
}
