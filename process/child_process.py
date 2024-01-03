from multiprocessing import Process
import os

def foo():
    print('foo child process', os.getpid())
    print('my parent is', os.getppid())
    print()

def bar():
    print('bar child process', os.getpid())
    print('my parent is', os.getppid())
    print()

def baz():
    print('baz child process', os.getpid())
    print('my parent is', os.getppid())
    print()


if __name__ == '__main__':
    print('parent process', os.getpid())
    print()
    child1 = Process(target=foo).start()
    child2 = Process(target=bar).start()
    child3 = Process(target=baz).start()