FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
RUN npm install react-leaflet@4.2.1
COPY . .
RUN npm run build

# Étape finale pour servir avec Nginx
FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

