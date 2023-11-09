FROM openjdk:17
LABEL authors="Elyor Azimov"

ENTRYPOINT ["top", "-b"]