#!/usr/bin/env python
import sys

current_word = None
current_count = 0
word = None
author_dict = {}

for line in sys.stdin:
    line = line.strip()
    items = line.split()
    print('"%s" "%s" "%s"' % (items[0], items[1], items[2]))
