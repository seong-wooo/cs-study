import threading
import os

def foo():
    print('foo thread id', threading.get_native_id())
    print('foo process id', os.getpid())
    print()

def bar():
    print('bar thread id', threading.get_native_id())
    print('bar process id', os.getpid())
    print()

def baz():
    print('baz thread id', threading.get_native_id())
    print('baz process id', os.getpid())
    print()

if __name__ == '__main__':
    print('process id', os.getpid())
    print()

    thread1 = threading.Thread(target=foo).start()
    thread2 = threading.Thread(target=bar).start()
    thread3 = threading.Thread(target=baz).start()
