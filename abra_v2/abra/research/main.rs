fn main() {
    let mut v = vec![1, 2, 3, 4];
    for x in v {
        println!("Hello {}", x);
        v[0] = 5
    }
}