# Use node 10
FROM node:10
ENV LAST_UPDATED 20160605T165400
ENV NODE_ENV development

# Copy source code
COPY . /app

# Change working directory
WORKDIR /app

# Install dependencies
RUN npm install

# Expose API port to the outside
EXPOSE 80

# Launch application
CMD ["npm","start"]