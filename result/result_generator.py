#!/usr/bin/env python

import csv

bs = {}  #result set

sable = {}
with open("filter_result_by_test_items.csv") as cfres:
	for line in cfres:
		rec = line.split(',')
		#threshold
		thres = 0.15 		
		if float(rec[2]) > thres: 
			if bs.has_key(rec[0]):
				bs[rec[0]].append(rec[1])
			else:
				bs[rec[0]] = []
				bs[rec[0]].append(rec[1])
	# for a in bs.items():
	# 	print a

with open("result_threshold_0.4.txt") as lshres:
	#head = [next(lshres) for x in xrange(10)]
	for line in lshres:
		v, k = line.split()[0].split(',')
		if sable.has_key(k):
			sable[k].append(v)
		else:
			sable[k] = []
			sable[k].append(v)
	# for b in sable.items():

with open("dim_fashion_matchsets.txt") as matchsets:
	for line in matchsets:
		cats = line.split()[1].split(';')
		for cat in cats:
			for item in cat.split(','):
				if sable.has_key(item):
					for tempcat in cats:
						if tempcat != cat:
							for tempitem in tempcat.split(','):
								for kk in sable[item]:
									if bs.has_key(kk):
										bs[kk].append(tempitem)
									else:
										bs[kk] = []
										bs[kk].append(tempitem)

with open("test_items.txt") as testset, open("test_item_result(0.15,0.4)","w") as res:
	# for a in bs.items():
	# 	res.write(str(a)+"\n")
	for line in testset:
		res.write(line.strip() + " "),
		if bs.has_key(line.strip()):
			for mi in bs[line.strip()]:
				res.write(mi+","),
		res.write("\n")






# with open("test_items.txt") as test_items:
# 	for line in test_items:
# 		pass
# 		#print line,

# 	pass



