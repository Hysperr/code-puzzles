#include <iostream>
#include <random>

template <typename T>
class my_unique_pointer {

typedef std::size_t size_type;

public:

explicit unique_pointer(T &t) : pointer(&t), count(1), ownership(true) {}
T *get() { return pointer; }
const T &operator*() const { return *pointer; }
bool has_ownership() const { return ownership; }

void reset(T &t) {
    delete pointer;
    count = 0;
    ownership = false;
    std::cout << "released...\n";

    pointer = t;
    ownership = true;
    count = 1;
    std::cout << "target acquired.\n";

}
private:

T *pointer;
size_type count;
bool ownership;
};


int main() {

    std::cout << "hello, world\n";

}