def get(angle):
     if (angle-180) < 0:
         return 180+angle
     else:
          return angle-180