const InvariantError = require('../../exceptions/InvariantError');
const {
  PostAuthenticationPayloadSchema,
  PostAuthenticationResponseSchema,
  PutAuthenticationPayloadSchema,
  PutAuthenticationResponseSchema,
  DeleteAuthenticationPayloadSchema,
  DeleteAuthenticationResponseSchema,
  PutChangePasswordResponseSchema,
  PutChangePasswordPayloadSchema,
} = require('../../validator/authentication/schema');

const routes = (handler) => [
  {
    method: 'POST',
    path: '/authentications',
    options: {
      handler: (request, h) => handler.postAuthenticationHandler(request, h),
      tags: ['api'],
      validate: {
        payload: PostAuthenticationPayloadSchema,
        failAction: (request, h, error) => {
          throw new InvariantError(error.message);
        },
      },
      response: { schema: PostAuthenticationResponseSchema },
    },
  },
  {
    method: 'PUT',
    path: '/authentications',
    options: {
      handler: (request, h) => handler.putAuthenticationHandler(request, h),
      tags: ['api'],
      validate: {
        payload: PutAuthenticationPayloadSchema,
        failAction: (request, h, error) => {
          throw new InvariantError(error.message);
        },
      },
      response: { schema: PutAuthenticationResponseSchema },
    },
  },
  {
    method: 'DELETE',
    path: '/authentications',
    options: {
      handler: (request, h) => handler.deleteAuthenticationHandler(request, h),
      tags: ['api'],
      validate: {
        payload: DeleteAuthenticationPayloadSchema,
        failAction: (request, h, error) => {
          throw new InvariantError(error.message);
        },
      },
      response: { schema: DeleteAuthenticationResponseSchema },
    },
  },
  {
    method: 'PUT',
    path: '/change-password',
    options: {
      handler: (request, h) => handler.changePasswordHandler(request, h),
      auth: 'interviewku_jwt',
      tags: ['api'],
      validate: {
        payload: PutChangePasswordPayloadSchema,
        failAction: (request, h, error) => {
          throw new InvariantError(error.message);
        },
      },
      response: { schema: PutChangePasswordResponseSchema },
    },
  },
];

module.exports = routes;
