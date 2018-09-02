const { env } = process;

module.exports = {
  siteMetadata: {
    title: 'Gatsby Default Starter',
  },
  plugins: [
    'gatsby-plugin-react-helmet',
    {
      resolve: 'gatsby-source-contentful',
      options: {
        spaceId: env['MMM_CONTENTFUL_SERVER_SPACE_ID'],
        accessToken: env['MMM_CONTENTFUL_SERVER_ACCESS_TOKEN'],
      },
    },
  ],
};
