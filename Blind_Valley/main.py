def recurse(d):
    d = d -1
    lv = d*d
    print("lv = ", d*d)
    if lv >= 5:
        return
    else:
        recurse(d)
    print("lv = ", d * d)
    return

recurse(0)
